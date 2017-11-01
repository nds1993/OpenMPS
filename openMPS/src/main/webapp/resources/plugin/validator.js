/**
 * 
 * <PRE>
 * 1. Project  : 모븐 프로젝트
 * 2. Comment : 유효성검사, 회원가입 데이터 양식 설정
 * 3. Author  : jbkm
 * 4. Updated Date    : 2014.11
 * 5. Updated History : 
 * </PRE>
 * 
 * <ul>
 * <li> Author : Date : Comment </li>
 * <li>--------------------------- </li>
 */

function isNumeric(val) {

	if (val == undefined)
		return;

	var re = /^[0-9]+$/;
	return re.test(val);

}

/**
 * 아이디 유효성 체크 ( 영문 소문자 , 숫자 5~12 )
 * 
 */ 

function isValidID(ObjUserID){
	
    if(ObjUserID.length < 5 || ObjUserID.length > 12)
    {
        return -1;
    }
    
    if(ObjUserID.match( /[^a-z0-9_-]/ ))
    {
        return -2;
    }
    
	return 1;
}

/**
 * 비밀번호 유효성 체크 ( 문자 , 숫자, 특수문자, 6~16 )
 * 
 */

function isValidPassword(ObjUserPassword){
	
	var num = ObjUserPassword.search(/[0-9]/g);
	var eng = ObjUserPassword.search(/[a-z]/ig);
	var spe = ObjUserPassword.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?.]/gi);
	
    if(ObjUserPassword.length < 6 || ObjUserPassword.length > 16)
    {
        return -1;
    }
    if(num < 0 || eng < 0 || spe < 0 )	
    {
        return -2;
    }
	
	return 1;
}


function isValidEmail(email_address) {

	if (email_address.match(/^([\w/g\.-]+)@(\w+)[.](\w+)$/ig) == null) {

		return false;
	}

	return true;
}

/**
 * 패스워드 동일여부 체크
 */
function isSamePassword(first_pw, second_pw){
	
	if(first_pw != second_pw){
		return false;
	}
	return true;
}

/**
 * 영문 + 숫자 체크 ( 한글 제외 )
 */
function isNotKorean(val){
	if (val == undefined)
		return;

	var re = /^[a-zA-Z0-9]+$/;
	return re.test(val);
}


/**
 * 한글 만 가능
 */
function isKorean(val){
	if (val == undefined){
		return;
	}
	
	var re = /^[가-힣]+$/;
	return re.test(val);
}

/**
 * 날짜 유효성 검증
 * 
 * yyyy/mm/dd or yyyy-mm-dd
 */
function isValidDate(inputText) {
	
	var isValid = false;
	
	try{
		
		var d = new Date(inputText);
		return true;
		
	} catch (e){}
	
	return false;
}


/**
 * Timestamp 리턴
 */
function returnTimestamp(month, date){
	
	var returnDate = new Date();
	
	returnDate.setUTCMonth(Number(month) - 1);
	returnDate.setUTCDate(date);
		    	
	returnDate.setUTCHours(00);	    	
	returnDate.setUTCMinutes(00);
	returnDate.setUTCSeconds(00);
	returnDate.setUTCMilliseconds(000);
	
	return returnDate.getTime() / 1000;
}

/**
 * returnDateLong 2014-06-30 형식의 string을 parameter로 받아 long 형태의 date를 return
 * 하는함수
 */
function returnDateLong1Params(dateString){
	/*
	 * var returnDate = new Date();
	 * 
	 * returnDate.setUTCFullYear(year, Number(month)-1, date);
	 * 
	 * returnDate.setUTCHours(00); returnDate.setUTCMinutes(00);
	 * returnDate.setUTCSeconds(00); returnDate.setUTCMilliseconds(000);
	 * 
	 * return returnDate.getTime();
	 */
	var dateArray = dateString.split('-');
	
	var year	= dateArray[0];
	var month	= dateArray[1];
	var date	= dateArray[2];
	
	var returnDate = new Date();
	
	returnDate.setFullYear(year, Number(month)-1, date);
		    	
	returnDate.setHours(00);	    	
	returnDate.setMinutes(00);
	returnDate.setSeconds(00);
	returnDate.setMilliseconds(000);
	
	return returnDate.getTime();
}

/**
 * returnDateLong 2014-06-30 형식의 year, month, date를 parameter로 받아 long 형태의 date를
 * return 하는함수
 */
function returnDateLong3Params(year, month, date){
	/*
	 * var returnDate = new Date();
	 * 
	 * returnDate.setUTCFullYear(year, Number(month)-1, date);
	 * 
	 * returnDate.setUTCHours(00); returnDate.setUTCMinutes(00);
	 * returnDate.setUTCSeconds(00); returnDate.setUTCMilliseconds(000);
	 * 
	 * return returnDate.getTime();
	 */
	
	var returnDate = new Date();
	
	returnDate.setFullYear(year, Number(month)-1, date);
		    	
	returnDate.setHours(00);	    	
	returnDate.setMinutes(00);
	returnDate.setSeconds(00);
	returnDate.setMilliseconds(000);
	
	return returnDate.getTime();
}

/**
 * Timestamp -> 날짜 변환 ( format - yyyymmddhhMMss )
 */
function convertTimestampToDate(timestamp){
	
	var theDate = new Date(timestamp * 1000);
	var conDate = "";
  
	dateString = theDate.toString("yyyyMMddhhmmss");
	
	
	/*
	 * arrDateStr = dateString.split(" ");
	 * 
	 * conDate += arrDateStr[3]; conDate += getMonthNum(arrDateStr[1]); conDate +=
	 * arrDateStr[2]; conDate += arrDateStr[4].substr(0,2); conDate +=
	 * arrDateStr[4].substr(3,2); conDate += arrDateStr[4].substr(6,2);
	 * 
	 * return conDate;
	 */
	return dateString;
}

/**
 * 실제 월 리턴
 */
function getMonthNum(abbMonth){
	var arrMon = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
	
	for(var i=0; i<arrMon.length; i++){
		
		if(abbMonth == arrMon[i]){
			
			var realMonth = i + 1;
			
			realMonth = String(realMonth);
						
			if(realMonth.length == 1){
				
				realMonth = "0" + realMonth;
			}
			
			return realMonth;
		}
	}
}

/**
 * 널체크
 */
function isEmpty(val){
	
	if(val != undefined
		&& val != null
		&& val != "undefined"
		&& val != ""	){
		
		return false;
	}
		
	return true;
}

/*
 * 전화번호 유효성체크
 */
function isValidPhonenumber(inputtxt)  
{  
  var phonenoregex = /^[0-9]+$/;
  
  if(inputtxt.length < 10 || inputtxt.length > 11)
  {
      return false;
  }
  
  if(phonenoregex.test(inputtxt))  
   {  
      return true;  
   }  
   else  
   {  
      return false;  
   }  
}
/*
 * 전화번호 - {3}-{3}-{3} 형태 혹은 {3}-{4}-{3}  포맷
 */
function autoHypenPhone(str){
	
	if(str == null) return "";
	
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 7){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else if(str.length == 10){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	}else if(str.length == 11){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}else{				
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
	return str;
}
