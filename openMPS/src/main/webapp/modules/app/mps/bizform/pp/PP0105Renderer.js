/**
 * Project : OpenMPS MIS
 *
 * 생산 > BOM 등록
 *
 */

define([
	"Logger",
	"NDSProps",
	"BaroBox",
	"FormBox",
	"WorkAreaHeader",
	"RendererBase",
	"WorkAreaRenderer2",
	"manifest!jqGrid4-1.0.0#widget",
	"APIClient",
	"CodeSearchRenderer"
], function(Logger, NDSProps, BaroBox, FormBox, WorkAreaHeader, RendererBase, WorkAreaRenderer2, JQGrid, APIClient, CodeSearchRenderer)
{
	/**
	 * BOM 트랜재션 데이타에서 그리드 영역을 취하는 코드를 변경한 그리드 Wrapper
	 */
	var BOMGrid = RendererBase.Grid.extend(
	{
		commit: function(params, cid, taskData, isValidRowChecker)
		{
			if( this.getTaskCount() > 0 || taskData )
			{
				var self = this;
				var id = this.get("id");
				var apiPath = this.get("client").getAPIPath("create", cid||id, params);
				var taskList = taskData || this._pool.getList();
				
				if( typeof isValidRowChecker == "function" )
				{
					var result = isValidRowChecker( "formBox", taskList.head );
					if( result != true )
					{
						// 커밋 조건에 해당하지 않는 task.
						return UCMS.retReject({resultCode:-2, msg:result||"Invalid parameters!!", extraData:{"id": id, "task": taskList.head}});
					}
					
					for(var i in taskList.detail)
					{
						result = isValidRowChecker( "resultBox.content", taskList.detail[i] );
						if( result != true )
						{
							// 커밋 조건에 해당하지 않는 task.
							return UCMS.retReject({resultCode:-2, msg:result||"Invalid parameters!!", extraData:{"id": id, "task": taskList[i]}});
						}
					}
				}

				return this.get("client").transaction( apiPath, taskList )
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						// 백업 데이타 갱신
						self.beginTransaction(true);
						self._pool.reset();
					}
					return res;
				});
			}
			else
			{
				return UCMS.retReject(null);
			}
		}
	});

	var Renderer = WorkAreaRenderer2.extend(
	{
		initialize: function(options)
		{
			Renderer.__super__.initialize.apply( this, arguments );
			this._contentId = "PP0105";
		}
		,
		initSubHeader: function()
		{
			Logger.debug("Renderer.initSubHeader()");

			var self = this;
			return this.queryHeaderInfo().then(function(headerParams)
			{
				//
				headerParams.options.feature =
				[
					{"load":"불러오기"},
					{"query":"조회"},
					{"create":"신규"},
					{"save":"저장"},
					{"cancel":"취소"}
				];

				RendererBase.Method.initSubHeader.call( self, headerParams );
			});
		}
		,
		getItemList: function()
		{
			return [
			        "headerBox",
			        "queryBox",
			        "formBox",
			        "resultBox",
			        "resultBox.header",
			        "resultBox.content"
			        ];
		}
		,
		getHeaderGridName: function()
		{
			return "resultBox.content";
		}
		,
		onInitRendererItemEvents: function()
		{
			var self = this;

			this.attachHeaderItem("headerBox", function(evt)
			{
				switch(evt.cmd)
				{
				case "content:load":
					// 등록된 bom 복사하기
					self.onLoadBOM();
					break;
				}
			});
			this.attachHeaderItem("resultBox.header", function(evt)
			{
				switch(evt.cmd)
				{
				case "content:create":
					self.onAddItem();
					break;
				case "content:delete":
					self.onDeleteItem();
					break;
				}
			});
			var queryItem = this.attachFormItem("queryBox");
			var $bomCode = queryItem.getItem().getWidget$Element().find("input.bomCode");
			$bomCode.keyup(function(event)
			{
				if( event.which == 13 )
				{
					var keyword = $bomCode.val();
					if( keyword.length > 0 )
					{
						self.findBOMCode( keyword );
					}
					else
					{
						self.onSearchBOM("");
					}
				}
			});
			/*
			 * 엔터키 핸들러와 중복 처리된다.
			queryItem.getItem().on(FormBox.EVENT.CHANGE, function(params)
			{
				if( params.id == "bomCode" )
				{
					if( params.value )
					{
						self.findBOMCode( params.value );
					}
					else
					{
						self.onSearchBOM("");
					}
				}
			});
			*/

			//
			var formItem = this.attachFormItem("formBox");
			formItem.getItem().disabled(true);
			formItem.getItem().on(FormBox.EVENT.CHANGE, function(item)
			{
				var formData = formItem.getItem().getItemData() || {};
				if(! formData.plantCode )
				{
					formData["plantCode"] = formItem.getItem().getWidget$Element().find(".plantCode select option:checked").val();
				}

				Logger.debug(item);
				if( item.id == "proCode" && typeof item.data != 'string' )
				{
					var newBomCode = item.data.result;
					if( newBomCode != formData.bomCode )
					{
						self.checkDupProCode({ "proCode" : item.data.result, "plantCode": formData.plantCode })
						.then(
								
							function(res)
							{
								if(res.extraData == "Y")
								{
									UCMS.alert("이미 공장에 등록된 코드입니다.");
									
									var clearForm = formItem.getItem().getWidget$Element();
									clearForm.find(".proCode_region input").val('');
								}
								else
								{
									formData.bomCode = newBomCode;
									formData.bomVer = 1;
									formData.proCode = item.data;
									formItem.getItem().setItemData( formData, true );
									
									//
									self.beginTransaction();
								}
							}
						);
							
					}
				}
				else
				{
					self.beginTransaction();
				}

				//
				self.updateTransactionData(formData);
			});

			//
			var gridItem = this.attachGridItem("resultBox.content");
			gridItem.getItem()
				.on(JQGrid.EVENT.CLICKBUTTON, function(btn)
				{
					self.downloadExcel("PP0105_grid_1","BOM_등록",false);
				});

			gridItem.getItem().on(JQGrid.EVENT.EDITCELL, function(item)
			{
				// { id:"row id", cell: {row: iRow, col: iCol, name: cellname, value: value} }
				if( item.cell.name == "buProCode" )
				{
					//
					// 부자재 코드 검색해서 이름 자동 등록
					//

					self.fetchBuProduct(item.cell.value)
					.then(function(res)
					{
						if( res.length == 1 )
						{
							Logger.debug(res);
							self.setBuProduct(item.id, res[0]);
						}
						else
						{
							self.popupBox("codesearch",
							{
								// 부자재 코드 조회
								"codeType": "PP0103",
								"keyword": item.cell.value,
					            "params": {
					            	"animalKind": "9"
					            },
								"callback": function(selected)
								{
									self.setBuProduct(item.id, selected);
								}
							});
						}
					})
					.fail(function(err)
					{
						UCMS.reportError(err);
					});
				}
				else
				{
					//
					// 부자재 코드 외의 컬럼
					//
					var gridItem = self.attachGridItem("resultBox.content");
					var rowData = gridItem.getItem().getRowData(item.id);

					if( item.cell.name == "proType" || item.cell.name == "qty" )
					{
						self.calcBOMWeight(gridItem);
					}

					gridItem.setRow(item.id, rowData);

					//
					self.beginTransaction();
				}
			});
			
			//
			UCMS.retry(function()
			{
				if( self.$el.find(".headerBox_region button.load").length == 0 )
				{
					return false;
				}
					
				self.$el.find(".headerBox_region button.load").prop("disabled", true);
			});
			
		}
		,
		checkDupProCode: function(params)
		{
			var self = this;
			return this._client.read(params, "dup")
		}
		,
		calcBOMWeight: function(gridItem)
		{
			var totalWeight = {};
			var proList = gridItem.getData();
			
			for(var i in proList)
			{
				totalWeight[proList[i].proType] || (totalWeight[proList[i].proType] = 0);
				// 동일 부자재 총중량 계산
				totalWeight[proList[i].proType] += Number(proList[i].proWeig) * Number(proList[i].qty);
			}

			// BOM 총량 갱신
			var headForm = this.attachFormItem("formBox");
			var bomData = headForm.getData();
			
			bomData.boxWeig = bomData.filmWeig = bomData.etcWeig = 0;
			_.map(totalWeight, function(weight, proType)
			{
				switch( proType )
				{
				case "1": // 박스
					bomData.boxWeig += weight;
					break;

				case "2": // 필름
					bomData.filmWeig += weight;
					break;

				case "3": // 기타
					bomData.etcWeig += weight;
					break;
					
				default:
					Logger.warn("Unknown proType : "+proType);
					break;
				}
			});
			
			if(bomData.boxWeig)
				bomData.boxWeig = Number(bomData.boxWeig).toFixed(2);
			if(bomData.filmWeig)
				bomData.filmWeig = Number(bomData.filmWeig).toFixed(2);
			if(bomData.etcWeig)
				bomData.etcWeig = Number(bomData.etcWeig).toFixed(2);
			
			bomData.totWeig = Number(bomData.boxWeig) + Number(bomData.filmWeig) + Number(bomData.etcWeig);
			bomData.totWeig = Number(bomData.totWeig).toFixed(2);
			
			headForm.setData( bomData );
		}
		,
		getClient: function()
		{
			if(! this._client )
			{
				var hosts = NDSProps.get('hosts') || { api: '' };
				this._client = new APIClient(
				{
					host: hosts.api,
					systemCode: NDSProps.get('systemCode'),
					contentId: this._contentId
				});
			}
			return this._client;
		}
		,
		getQueryData: function()
		{
			var queryData = this.getRendererItem("queryBox").getItemData();
			return queryData;
		}
		,
		setBOMInfo: function(bom)
		{
			if(typeof bom.proCode == "string" )
			{
				// 코드 검색창에 설정을 위해서 포멧 변환
				bom.proCode = { result: bom.proCode, keyword: bom.proName };
			}
			// 쿼리 박스 채우기
			var queryData = {
				"plantCode": bom.plantCode,
				"bomCode": bom.bomCode,
				"bomVer": bom.bomVer,
				"proCode": bom.proCode
			};
			this.getRendererItem("queryBox").setItemData(queryData);
			this.getRendererItem("formBox").setItemData(bom, true);

			// BOM 정보 설정
			var self = this;

			this.showLoading();
			return this.fetchDetail( bom )
			.then(function()
			{
				self.getRendererItem("formBox").disabled(false);
			})
			.always(function()
			{
				self.hideLoading();
			});
		}
		,
		onLoadBOM: function()
		{
			var self = this;
			
			var formItem = this.attachFormItem("formBox");
			var plantCode = formItem.getItem().getWidget$Element().find(".plantCode select option:checked").val();
			
			this.popupBox("PP0105_pop_01",
			{
				callback: function(selected)
				{
					// {id:#, data: {}}
					if( selected )
					{
						self._loadBOM = selected.data;
						self.beginTransaction();
						self.setBOMInfo(selected.data);
						self.createTransactionData(selected.data);
						self._dsType = 'C';
						
						// 선택된 공장코드 재설정
						var newBOMHead = formItem.getItem().getItemData();
						newBOMHead.plantCode = plantCode;
						formItem.getItem().setItemData( newBOMHead, true );
					}
				}
			});
		}
		,
		onSearchBOM: function(keyword)
		{
			var self = this;
			var queryData = this.getQueryData();
			this.popupBox("PP0105_pop_02",
			{
				plantCode: queryData.plantCode,
				keyword: keyword,
				callback: function(result)
				{
					// {plantCode:{}, product:{}, bom:{}}
					if(! result )
					{
						return;
					}
					var bomData =
					{
						"bomCode":result.bom.bomCode,
						"bomVer":result.bom.bomVer,
						"plantCode":result.plantCode,
						"proCode":result.product,
						"totWeig":result.bom.totWeig,
						"boxWeig":result.bom.boxWeig,
						"filmWeig":result.bom.filmWeig,
						"etcWeig":result.bom.etcWeig,
						"memo":null
					};
					self.setBOMInfo(bomData);
					this._dsType = 'U';
				}
			});
		}
		,
		/**
		 * 신규 BOM 작성 모드로 전환
		 */
		onCreate: function()
		{
			var formBox = this.getRendererItem("formBox");
			formBox.disabled(false);
			formBox.clear();
			var item = formBox.getItemElement("plantCode");
			if( item.jquery )
			{
				item.find("select").focus();
			}
			else if( item.getWidget$Element )
			{
				item.getWidget$Element().find("select").focus();
			}
			this.getRendererItem("resultBox.content").clear();
			this.getRendererItem("queryBox").clear();

			//
			this.beginTransaction();
			this.createTransactionData();
			this._dsType = 'C';
			
			//
			this.$el.find(".headerBox_region button.load").prop("disabled", false);
		}
		,
		onCancel: function()
		{
			Renderer.__super__.onCancel.call( this );
			this._dsType = 'U';
			this.$el.find(".headerBox_region button.load").prop("disabled", true);
			UCMS.alert("취소되었습니다.");
		}
		,
		onQuery: function(featureId, options)
		{
			var queryData = this.getQueryData();
			if(!queryData.bomCode || this._prevBomCode == queryData.bomCode)
			{
				options || (options={noti:true});
				if( options.noti != false )
				{
					// queryBox 입력박스 핸들러에 의해 조회를 위한 팝업창이 출력된다.
					UCMS.alert("조회할 BOM 을 선택하세요.");
				}
				return;
			}
			else
			{
				// bomCode 의 입력값이 변경되지 않은경우에는 Form Change 이벤트가 발생되지 않는다.
				// 이럴때는 아무런 팝업이 띄워지지 않기때문에 UI 가 반응없는 상태로 보이기 때문에
				// 안내 메시지를 띄워야 한다.
				this._prevBomCode = queryData.bomCode;
			}
			if(!queryData.proCode.result)
			{
				// bomCode change 핸들러에서 검색을 수행한다.
				return;
			}

			var self = this;

			this.showLoading();
			this.fetchHead()
			.done(function(res)
			{
				if( res )
				{
					self.getRendererItem("formBox").disabled(false);
					self.fetchDetail(res.extraData)
					.then(function()
					{
						self._dsType = 'U';
					});
				}
			})
			.always(function()
			{
				self.hideLoading();
				self._prevBomCode = null; 
			});
		}
		,
		onAddItem: function()
		{
			var formData = this.getRendererItem("formBox").getItemData();
			if( !formData || formData.proCode.result.length == 0 )
			{
				UCMS.alert("등록 대상이 되는 제품을 선택하세요.");
				return;
			}

			this.beginTransaction();
			var gridItem = this.attachGridItem("resultBox.content");
			gridItem.createRow({"proWeig":0});
		}
		,
		onDeleteItem: function()
		{
			var gridItem = this.attachGridItem("resultBox.content");
			var id = gridItem.getItem().getSelRowId();
			if( id != null )
			{
				this.beginTransaction();
				gridItem.removeRow( id );
				this.calcBOMWeight( gridItem );
			}
			else
			{
				UCMS.alert("삭제할 부자재를 선택하세요.");
			}
		}
		,
		fetchHead: function()
		{
			var self = this;
			var formBox = this.getRendererItem("formBox");
			var queryData = this.getQueryData();

			formBox.clear();
			return this.getClient().read(
			{
				plantCode: queryData.plantCode,
				bomCode: queryData.proCode.result,
				bomVer: queryData.bomVer
			}, "form")
			.then(function(res)
			{
				if( res.resultCode == 0 && res.extraData )
				{
					var bomInfo = res.extraData;
					if( typeof bomInfo.proCode == "string" )
					{
						bomInfo.proCode = {
							result: bomInfo.proCode,
							keyword: bomInfo.proName
						};
					}
					formBox.setItemData( bomInfo );
					
					// bomVer 반영
					self.updateBomVer( bomInfo.bomVer );
				}
				else
				{
					UCMS.alert("BOM 이 존재하지 않습니다.");
					self.getRendererItem("resultBox.content").clear();
					res = null;
				}

				return res;
			})
			.fail(function(err)
			{
				UCMS.reportError(err);
			});
		}
		,
		updateBomVer: function(bomVer)
		{
			var queryBox = this.getRendererItem("queryBox");
			var data = queryBox.getItemData();
			data.bomVer = bomVer;
			queryBox.setItemData(data);
			
			var formBox = this.getRendererItem("formBox");
			data = formBox.getItemData();
			data.bomVer = bomVer;
			formBox.setItemData(data );
		}
		,
		/**
		 * BOM 상세 정보 조회
		 * @param {object} bomHeaderData - BOM 헤드 정보. 지정하지 않은 경우 queryBox 에서 취득된다.
		 * @return {$.xhr}
		 */
		fetchDetail: function(bomHeaderData)
		{
			var queryData = this.getQueryData();
			if(! queryData.bomCode)
			{
				UCMS.alert("조회할 BOM 을 선택하세요.");
				return UCMS.retReject();
			}
			return Renderer.__super__.fetchingGrid.call( this, "detail", {noti:false, params:bomHeaderData} )
			.then(function(res)
			{
				if( res.resultCode == 0 && res.extraData.result.length == 0)
				{
					UCMS.alert("등록된 부자재가 없습니다.");
				}
			});
		}
		,
		/**
		 * 지정한 키워드에 해당하는 제품을 검색한다.
		 */
		fetchProduct: function(keyword)
		{
			return CodeSearchRenderer.prototype.searchCode(CodeSearchRenderer.TYPE.PRODUCT,
			{
				"animalKind": 1,
				searchKeyword: keyword
			})
			.then(function(res)
			{
				if( res instanceof Array == false )
				{
					return null;
				}

				// 검색된 제품 코드를 반환한다.
				return res;
			});
		}
		,
		/**
		 * 부자재 코드 검색
		 */
		fetchBuProduct: function(keyword)
		{
			return CodeSearchRenderer.prototype.searchCode(CodeSearchRenderer.TYPE.PRODUCT,
			{
				searchKeyword: keyword,
				animalKind: 9
			})
			.then(function(res)
			{
				if( res instanceof Array == false )
				{
					return null;
				}

				// 검색된 제품 코드를 반환한다.
				return res;
			});
		}
		,
		/**
		 * 부자재 목록에 부자재 코드 입력
		 */
		setBuProduct: function(rowId, buProd)
		{
			if(! buProd )
			{
				return;
			}

			var gridItem = this.attachGridItem("resultBox.content");
			gridItem.setCell( rowId, "buProCode", buProd.code );
			gridItem.setCell( rowId, "buProName", buProd.label );
			gridItem.setCell( rowId, "proWeig", buProd.proWeig );
			gridItem.setCell( rowId, "proUnit", buProd.proUkind );

			//
			this.beginTransaction();
		}
		,
		onSave: function()
		{
			if(! this._dsType )
			{
				UCMS.alert("저장될 작업이 없습니다!");
				return;
			}

			var self = this;
			UCMS.confirm("진행중인 작업을 저장할까요?", {confirm:"저장", cancel:"취소"})
			.then(function()
			{
				var gridItem = self.attachGridItem("resultBox.content");
				var taskData = self.updateTransactionData( self.getRendererItem("formBox").getItemData() );
				if( self._loadBOM )
				{
					//
					// BOM 신규 생성
					//
					if( self._loadBOM.plantCode == taskData.head.plantCode )
					{
						UCMS.alert("불러오기된 BOM 정보는 새로운 공장에만 저장할 수 있습니다.");
						return;
					}

					// 무조건 U 로 추가한다.
					taskData.detail = _.map(gridItem.getData(), function(item)
					{
						item.dsType = "U";
						return item;
					});
				}
				else
				{
					//
					// 수정된 상세 정보 저장
					//

					// 기존 정보 추가
					taskData.detail = _.map(gridItem.getData(), function(item)
					{
						item.dsType = "U";
						return item;
					});

					// 삭제 정보 추가
					_.map(gridItem.getTaskList(), function(item)
					{
						if( item.dsType == "D" )
						{
							taskData.detail.push(item);
						}
					});
				}

				if( taskData.detail.length == 0 )
				{
					UCMS.alert("BOM 상세 정보를 입력하세요.");
					return;
				}
				taskData.dsType = self._dsType;
				self.saveTransaction(null, true, taskData)
				.then(function(res)
				{
					if( res.resultCode == 0 )
					{
						self.updateBomVer( res.extraData.head.bomVer );
						self.$el.find(".headerBox_region button.load").prop("disabled", true);
						UCMS.alert("저장되었습니다.");
					}
					else
					{
						UCMS.reportError(res);
					}
				})
				.fail(function(e)
				{
					UCMS.reportError(e);
				});
			});
		}
		,
		/**
		 * BOM 트랜잭션을 생성한다.
		 * dsType 은 'C' 로 설정된다.
		 * @return {object} 생성된 트랜잭션 객체
		 */
		createTransactionData: function(head, detail)
		{
			this._transData =
			{
				"head" : head || null,
				"detail" : detail || null,
				"dsType" : "C"
			};
			return this._transData;
		}
		,
		/**
		 * BOM 트랜젝션을 갱신한다.
		 * dsType 은 'U' 로 설정된다.
		 * @return {object} 갱신된 트랜잭션 객체
		 */
		updateTransactionData: function(head, detail)
		{
			this._transData || this.createTransactionData();
			if( head )
			{
				if( head.proCode && typeof head.proCode != "string" )
				{
					head.proCode = head.proCode.result;
				}
				this._transData.head = head;
			}
			if( detail )
			{
				this._transData.detail = detail;
			}
			this._transData.dsType = "U";
			return this._transData;
		}
		,
		setUpdateMode: function(bUpdate)
		{
			var $form = getRendererItem("formBox").getWidget$Element();

			$form.find("input.bomVer").prop("disabled", bUpdate == true);
			$form.find(".plantCode select").prop("disabled", bUpdate == true);
			if( bUpdate )
			{
				$form.find(".plantCode select").parent().addClass("disabled");
			}
			else
			{
				$form.find(".plantCode select").parent().removeClass("disabled");
			}
		}
		,
		attachGridItem: function(itemId, cid)
		{
			if(! this._items[itemId] )
			{
				var grid = this.getItemInstance( itemId );
				if(! grid )
				{
					Logger.warn("attachGridItem() - Unknown Grid Item : "+itemId);
					return null;
				}
				var options =
				{
					id : itemId,
					item: grid,
					client: this.getClient(cid)
				};
				this._items[itemId] = new BOMGrid(options);
			}
			return this._items[itemId];
		}
		,
		/**
		 * Task commit 시 데이타의 유효성을 검증한다.
		 * 기본적으로 true 를 반환하며, 검증이 필요한 랜더러에서 상속 받아서 제공되는 Task 정보의 유효성 검증 결과를 boolean 값으로 반환처리한다.
		 * @param {string} gridId - commit 이 발생된 그리드의 식별자
		 * @param {object} taskItem - 트랜잭션 항목 객체
		 * @return {boolean} false 를 리턴하면, 해당 commit 동작은 reject 되며, 문제가 된 task 항목에 대한 정보 { id, task } 가 반환된다.
		 */
		isValidRowChecker: function(gridId, taskItem)
		{
			if( gridId == "formBox" )
			{
				return ( taskItem.bomCode && taskItem.bomVer && taskItem.proCode && taskItem.plantCode ) ? true : false;
			}
			else if( gridId == "resultBox.content" )
			{
				if( taskItem.qty <= 0 )
				{
					return "\"소요량\"은 0 보다 큰 수로 입력해 주세요.";
				}
				return ( taskItem.proType && taskItem.buProCode && taskItem.qty ) ? true : false;
			}
			return true;
		}
		,
		onSearchCode: function(options)
		{
			if( options.params.queryBox == true )
			{
				var queryItem = this.attachFormItem("queryBox");
				queryItem.setData(_.extend(queryItem.getData(), {bomVer:"",bomCode:""}));
				
				var keyword = options.keyword;
				if( keyword.length > 0 )
				{
					this.findBOMCode( keyword );
				}
				else
				{
					this.onSearchBOM("");
				}
			}
			else
			{
				Renderer.__super__.onSearchCode.call( this, options );
			}
		}
		,
		findBOMCode: function(keyword)
		{
			var self = this;
			
			var queryItem = this.attachFormItem("queryBox");
			this.fetchProduct(keyword)
			.then(function(products)
			{
				if( products )
				{
					Logger.debug(products);

					// 여러개의 제품이 반환되는 경우 제일 처음 것이 사용된다.
					if( products.length > 1 )
					{
						self.onSearchBOM(keyword);
					}
					else if( products.length == 1 )
					{
						var queryData = _.extend(queryItem.getData(),
						{
							bomCode: products[0].code,
							proCode: {
								result: products[0].code,
								keyword: products[0].label,
							}
						});
						queryItem.setData(queryData);
						self.onQuery();
					}
					else
					{
						self.onSearchBOM(keyword);	
					}
				}
				else
				{
					self.onSearchBOM(keyword);
				}
			})
			.fail(function()
			{
				self.onSearchBOM(keyword);
			})
			.always(function()
			{
				self._dsType = 'U';
			});
		}
	}
	,
	{
		EVENT:
		{
		}
	});

	return Renderer;
});
