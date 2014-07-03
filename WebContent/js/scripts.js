var request;
/*ajax 与后台交互*/
function register_check() {
	var msg;
	var c_id = document.getElementById("r_id");
	var c_name = document.getElementById("r_name");
    var c_password = document.getElementById("r_pwd");  
    var c_confirmPwd = document.getElementById("r_confirm");
    var id = c_id.value;
    var name = c_name.value;
    var password = c_password.value;  
    var confirmPwd = c_confirmPwd.value;
    //清除已有提醒信息
    document.getElementById("id_msg").innerHTML=null;
    document.getElementById("name_msg").innerHTML=null;
    document.getElementById("pwd_msg").innerHTML=null;
    document.getElementById("confirm_msg").innerHTML=null;
    if (id == "") {  
        msg="<font color='red'>编号不能为空!</font>";  
        document.getElementById("id_msg").innerHTML=msg;  
        c_password.focus();  
        return false;  
    } 
    else if (name == "") {  
        msg="<font color='red'>用户名不能为空!</font>";  
        document.getElementById("name_msg").innerHTML=msg;  
        c_name.focus();  
        return false;  
    } 
    else if (password == "") {  
        msg="<font color='red'>密码不能为空!</font>";  
        document.getElementById("pwd_msg").innerHTML=msg;  
        c_password.focus();  
        return false;  
    }  
    else if (password != confirmPwd) {  
        msg="<font color='red'>前后密码不一致!</font>";  
        document.getElementById("confirm_msg").innerHTML=msg;  
        c_confirmPwd.focus();  
        return false;  
    }
    return true;  
} 
function checkName(){
	var name = document.getElementById("r_name");
	$.ajax({
		type:"post",
		url:"RegisterCheckAction.do?r_name="+name.value, 
		success:function(result){
			if(result=='exist'){
				document.getElementById("name_msg").innerHTML="<font color='red'>用户名已存在！</font>";
			}
		}
	});
}





