/**
 * Project : T-Platform
 *
 * Copyright (c) 2017 FREECORE, Inc. All rights reserved.
 */

define([
"Logger",
"NDSProps",
"manifest!jqGrid4-1.0.0",
"moment"
],
function(Logger, NDSProps, JQGrid, moment)
{
	// type = close 이면 무전건 닫음 닫혀 있어도 닫음
	UCMS.onMenuSlide = function(type)
	{
		var thisType = type // 무조건 닫게 하기 위해 필요
		var menuWidth = "230px"; // 메뉴 가로 크기
		var self = this;
		/* 메뉴 변경시 변경사항
		 * 메뉴 사라질때
		 * .workarea_region 변경 (left:190px > left:0)
		 * .menu_tab 변경			(left:190px > left:0)
		 * .gnbarea_box 변경		(left:0px > left:190px)
		 * .menu_slide_box button .fa  (.fa-chevron-left > .fa-chevron-right)
		 * .workarea_box (min-width : 1090px > 1280px )
		 * 해당 값은 css 에 따라 달리 주어야 하며, 이곳의 수치를 변경시 해당 css 의 수치를 반드시 수정하여야 합니다.
		 */
		var fullMinWidth = "1280px"; // 전체 최소 가로 크
		var workMinWidth = "1050px" ; // 업무영역 최소 가로 크기
		if (($(".workarea_region").css("left") == menuWidth) || (thisType=="close")){
			// 메뉴 접음
			$(".menu_slide_box").css("left","0");
			$(".workarea_region").css("left","0");
			$(".menu_tab").css("left","0");
			$(".workarea_box").css("minWidth",fullMinWidth);
			$(".gnbarea_box").css("left","-"+menuWidth);
			$(".menu_slide_box button .fa").removeClass("fa-chevron-left");
			$(".menu_slide_box button .fa").addClass("fa-chevron-right");
		} else {
			// 메뉴 폄
			$(".menu_slide_box").css("left",menuWidth);
			$(".workarea_region").css("left",menuWidth);
			$(".menu_tab").css("left",menuWidth);
			$(".workarea_box").css("minWidth",workMinWidth);
			$(".gnbarea_box").css("left","0");
			$(".menu_slide_box button .fa").removeClass("fa-chevron-right");
			$(".menu_slide_box button .fa").addClass("fa-chevron-left");
		}
	};
	/**
	 * 공통 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboItems = function(res)
	{
		return _.map(res.extraData.result, function(item)
	    {
	    	return {
	        	label: item.codeName,
	            value: item.codeId
	        };
	    });
	};
	/**
	 * Parser 에 "전체" 추가한다.
	 * items : parser
	 */
	UCMS.parseComboAll = function(items)
	{
		items.unshift({
			label: "전체",
			value: 0
		});
		return items;
	};
	/**
	 * Parser 에 "선택" 추가한다.
	 * items : parser
	 */
	UCMS.parseComboRequired = function(items)
	{
		items.unshift({
			label: "선택",
			value: 0
		});
		return items;
	};

	/**
	 * 거래처 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboCust = function(res)
	{
		return res.extraData.result;
	};
	/**
	 * 제품 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboPro = function(res)
	{
		return res.extraData.result;
	};

	/**
	 * 공장 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboPlant = function(res)
	{
		return _.map(res.extraData.result, function(item)
	    {
	    	return {
	        	label: item.plantName,
	            value: item.plantCode
	        };
	    });
	};

	/**
	 * 영업 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboSalesHead = function(res)
	{
		return _.map(res.extraData.result, function(item)
	    {
	    	return {
	    		label: item.headName,
	            value: item.headCode
	        };
	    });
	};

	/**
	 * 영업 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboSalesTeam = function(res)
	{
		return _.map(res.extraData.result, function(item)
	    {
	    	return {
	    		label: item.teamName,
	            value: item.teamCode
	        };
	    });
	};


	/**
	 * 출고창고 코드를 폼 콘트롤 아이템 combobox 의 아이템 목록을 파징한다.
	 */
	UCMS.parseComboDc = function(res)
	{
		return _.map(res.extraData.result, function(item)
	    {
	    	return {
	        	label: item.label,
	            value: item.code
	        };
	    });
	};

	/**
	 * 영업사원의 거래처 목록을 combobox 아이템 목록 형식으로 변환한다.
	 * /rest/mpm/1001/sd0103/mpsalesmancust 에 대한 파서
	 */
	UCMS.parseComboSalesmanCust = function(res)
	{
		return _.map(res.extraData.result, function(item)
	    {
	    	return {
	        	label: item.salesmanCustname,
	            value: item.salesmanCust
	        };
	    });
	};

	/**
	 * 로그인된 사용자 정보를 콤보 아이템 형식으로 반환한다.
	 */
	UCMS.parseSessionUser = function()
	{
		var userId = NDSProps.get("user").id;
		return UCMS.retResolve(
		[
		{
			label: userId,
			value: userId
		}
		]);
	};

	/**
	 * 세션 정보 기반한 영업 파트 콤보 목록 조회
	 */
	UCMS.parseSessionTeam = function()
	{
		var user = NDSProps.get("user");
		return UCMS.retResolve(
		[
			{
				label: user.teamName,
				value: user.teamCode
			}
		]);
	};
	
	/**
	 * 세션 정보 기반한 직책 조회
	 */
	UCMS.parseSessionOfce = function()
	{
		var user = NDSProps.get("user");
		return UCMS.retResolve(
		[
			{
				label: user.ofceName,
				value: user.ofceCode
			}
		]);
	};

	/**
	 * 지정한 Box 리소스를 팝업시킨다.
	 */
	UCMS.popupBox = function(boxId, options)
	{
		var boxRepo = NDSProps.get("BoxRepo");
		var appName = NDSProps.get("appName");

		UCMS.loadBoxResource( boxRepo[boxId].detail )
		.then(function(boxParams)
		{
			options || (options={});
			UCMS.loadModuleByPath(["BaroFloating", boxParams.module])
			.then(function(BaroFloating, Module)
			{
				var cbResult = function(result)
				{
					if( options.callback )
					{
						options.callback( result );
					}
					BaroFloating.close();
				};

				BaroFloating.open
				(
					Module,
					_.extend(options, boxParams.options),
					{
						title: appName,
						close: function()
						{
							cbResult(null);
						}
						,
						className: "modal_box "+boxId
					}
				)
				.done(function(result)
				{
					cbResult(result);
				});
			});
		});
	};

	UCMS.msgBox = function(msg, title, btnLabels)
	{
		var appName = NDSProps.get("appName");
		if( title )
		{
			if( typeof title !== "string" )
			{
				// title 을 생략한 경우
				btnLabels = title;
				title = appName;
			}
		}
		else
		{
			title = appName;
		}
		var box = "<div class=\"popup_no_icon\"></div><div class=\"popup_no_icon_box\">" +
			"<div class=\"title\">"+title+"</div> " +
			"<div class=\"body\">" +
			"<textarea class=\"form-control\" style=\"height:100px\">"+
			msg+
			"</textarea></div></div>";

		if( btnLabels )
		{
			return UCMS.confirm( box, btnLabels );
		}
		else
		{
			return UCMS.alert( box, title );
		}
	};

	UCMS.boxHolder = function(box, btnLabel)
	{
		var d = $.Deferred();

		btnLabel = btnLabel || {};
		var confirmLabel = btnLabel.confirm || "확인";
		var cancelLabel = btnLabel.cancel || "취소";

		if($("#confirm_box").length == 0)
		{
			var alert_str = '';
			alert_str += '<div id="confirm_box" class="modal_box">';
			alert_str += '<div class="alert_box">';
			alert_str += '	<div class="alert_text">';
			alert_str += '		<i class="fa fa-info-circle color_2nd fa-3x"></i>';
			alert_str += '		<div class="content"> ' +box+ '</div>';
			alert_str += '	</div>';
			alert_str += '	<div class="alert_btn_box">';
			alert_str += '		<button type="button" class="btn btn-lg btn_go_cancel btn-default w40p"><i class="fa fa-close"></i> '+cancelLabel+'</button>';
			alert_str += '		<button type="button" class="btn btn-lg btn_go_cofirm btn-primary w40p ml10"><i class="fa fa-check color_2nd"></i> '+confirmLabel+'</button>';
			alert_str += '	</div>';
	    	alert_str += '</div></div>';

			$("body").append(alert_str);

			$(".alert_btn_box button.btn_go_cofirm").click(function()
	    	{
				var $ta = $("#confirm_box textarea");
				var msg = null;
				if( $ta.length > 0 )
				{
					msg = $ta.val();
				}
	    		d.resolve(msg);
	    		$("#confirm_box").remove();
	    	})
	    	.focus();

			$(".alert_btn_box button.btn_go_cancel").click(function()
	    	{
	    		$("#confirm_box").remove();
	    		d.reject();
	    	});
		}
		else
		{
			// 활성화된 상태에서 재진입인 경우.
			d.reject();
		}

		return d.promise();
	};

	/**
	 * @return {$.promise}
	 */
	UCMS.inputBox = function(msg, title, btnLabels)
	{
		var appName = NDSProps.get("appName");
		if( title )
		{
			if( typeof title !== "string" )
			{
				// title 을 생략한 경우
				btnLabels = title;
				title = appName;
			}
		}
		else
		{
			title = appName;
		}
		var box =
		"<div class=\"popup_no_icon\"></div>" +
		"<div class=\"popup_no_icon_box\">" +
			"<div class=\"title\">"+title+"</div> " +
			"<div class=\"body\">" +
				"<textarea class=\"form-control\" style=\"height:100px\" placeholder=\""+msg+"\"></textarea>" +
			"</div>" +
		"</div>";

		return UCMS.boxHolder( box, btnLabels );
	};

	/**
	 * 지정한 그리드 옵션에 "엑셀 파일로 다운로드" 버튼을 추가한다.
	 */
	UCMS.appendExcelBtn = function(gridOptions)
	{
		gridOptions.navGrid = {};
		gridOptions.buttons = [
		           			{
		        				"caption":"엑셀",
		        				"buttonicon":"ui-icon-disk",
		        				"position": "last",
		        				"title":"그리드 데이타 엑셀로 받기",
		        				"cursor": "pointer"
		        			}];

		return gridOptions;
	};

	/**
	 * 그르드 셀용 캘린더를 팝업한다.
	 * @param {string} rowId - 행 식별자
	 * @param {string} celName - 대상 셀 네임
	 * @param {object} options - 캘린더 옵션. datetimepicker 옵션을 지정한다.
	 * @returns {$} 대상이 되는 셀의 $
	 */
	UCMS.popupGridCalender = function(rowId, celName, options)
	{
		var $cell = $("#"+rowId+" input[name="+celName+"]");
		$cell.datetimepicker(_.extend({"format": 'YYYY-MM-DD', "useCurrent":true}, options));
		$cell.data("DateTimePicker").defaultDate(new Date());
		$cell.data("DateTimePicker").show();
		return $cell;
	}
	
	/**
	 * 문자열의 bytes 를 구한다.
	 */
	UCMS.getByteLength = function(s,b,i,c)
	{
	    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
	    return b;
	}

//////
	// jqGrid Custom Formatter
	//
	var jqGridFormatter =
	{
		dateFormatter : function(cellvalue, options, rowObject)
		{
			return cellvalue ? moment(cellvalue).format(options.colModel.formatoptions.newformat) : "";
		}
		,
		dateUnformatter : function(cellvalue, options, rowObject)
		{
			return cellvalue ? moment(cellvalue).format(options.colModel.formatoptions.srcformat) : "";
		}
		,
		/**
		 * 할인단가 승인 일자를 출력한다.
		 * 반려된 경우는 날짜 변환외에 "반려" 를 반환한다.
		 */
		SD0205_DateFormatter : function(cellvalue, options, rowObject)
		{
			var approYn = "N";
			switch( options.colModel.name )
			{
			case "partDate":
				approYn = rowObject.partAppro;
				break;

			case "headDate":
				approYn = rowObject.headAppro;
				break;

			case "ceoDate":
				approYn = rowObject.ceoAppro;
				break;
			}
			if( approYn == "Y" )
			{
				return cellvalue ? moment(cellvalue).format(options.colModel.formatoptions.newformat) : "";
			}
			else if( approYn == "X" )
			{
				return "반려";
			}
			else
			{
				return "";
			}
		}
		
		,
		/**
		 * 생산 일자를 출력한다.
		 * 날짜형식 외의 데이터는 각 공장, 제품명에 대한 건수를 출력한다.
		 */
		PP0601_DateFormatter : function(cellvalue, options, rowObject)
		{
			//var approYn = "N";
			
			Logger.debug("rowObject>");
			Logger.debug(rowObject);
			
			
			if(rowObject.nongcode != null){
				return cellvalue ? moment(cellvalue).format(options.colModel.formatoptions.newformat) : "";
			}
			else{
				if(rowObject.sandate == null){
					return "";
				}
				else{
					var result = rowObject.cnt + "건";
					return result;
				}
				
			}
			
			
		}
		,
		
		/**
		 * 제품명을 출력한다.
		 * 제품명 이외의 데이터는 소계로 출력한다.
		 */
		PP0601_ProductTotal : function(cellvalue, options, rowObject){
			
			if(rowObject.proname == null){
				var result = rowObject.cnt + "건";
				return result;
			}
			else{
				return cellvalue;
			}
		}
	};
	// jqGrid 모듈이 로딩되면서 $.fn.fmatter 가 설정된다.
	jQuery.extend($.fn.fmatter, jqGridFormatter);

	return UCMS;
});
