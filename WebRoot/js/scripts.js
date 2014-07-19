var register=function(){

    if($('#r_pswd').val()===''){
        alert('亲，你还没有输入密码呢')
        return false
    }else if($('#r_pswd').val() !== $('#r_confirm').val()){
        alert('亲，两次密码不正确哦')
        return false
    }else{
        $.post('registerAction.do',
            {
               name : $('#r_name').val(),
               pwd : $('#r_pswd').val(),
               id : $('#r_email').val()
            },
            function(data,status){
                 var response=JSON.parse(data);
                 if(response['error']==0){
                    alert('注册成功!');
                 }else{
                    alert('注册失败,原因:'+response['reason'])
                 }
              }
            )
   }
}

var login=function(){
    $.post('loginAction.do',
        {
           uid : $('#l_name').val(),
           pwd : $('#l_pswd').val()
        },
        function(data,status){
             var response=JSON.parse(data);
             if(response['error']==0){
                //console.log("http://localhost:8888/tower/"+response['redirect'])
                location.href="http://localhost:8888/tower/"+response['redirect']
             }else{
                alert('登录失败,原因:'+response['reason'])
             }
        }
    )
}

var getuserInfo=function(){
    $.post('imyselfAction.do',
        {
        
        },
        function(data,status){
             var response=JSON.parse(data)
             $('#username').html(response['userinfo']['user_name'])
             $('#email').html(response['id'])
        }
    )
}

var getTask=function(){
    $.post('imyselfAction.do',
        {
            
        },
        function(data,status){
             var response=JSON.parse(data)
             var tasks=response['tasks']
             var chunk=''
             for(var i=0;i<tasks.length;i++){
                //console.log(tasks[i].task_name)
                chunk+=filltasklist(tasks[i].group_name,tasks[i].task_name,tasks[i].task_detail,tasks[i].task_deadline)
             }
             $('.main_content').html(chunk)
        }
    )
}

var getReport=function(){
    var commentHTML="<div class='division'></div><div id='hook'></div><form method = 'post'><div class='col-md-12 add_project_member_info'><div class='col-md-2 replybox_header'></div><div class='col-md-12 add_project_member_info_input'><div class='col-md-6 replybox_title'><input type='text' id='report-title' class='form-control' placeholder='写个标题吧....'></div><div class='col-md-6 replybox_content'><textarea class='form-control' id='report-content' rows='4' placeholder='写个报告吧....'></textarea></div></div></div><div class='clearfix'></div><div class='col-md-12 add_project_choice'><a class='col-md-2 col-md-offset-1 btn btn-success' href='javascript:void(0)' onclick='addReport()'>创建项目</a><a class='col-md-2 btn' href='#'>取消</a></div></form></div>"
    $.post('imyselfAction.do',
        {
            
        },
        function(data,status){
             var response=JSON.parse(data)
             var reports=response['reports']
             var goupid="<input type='hidden' id='groupid' value='"+response['reports'][0]['group_id']+"'>"
             var chunk=''
             for(var i=0;i<reports.length;i++){
                //console.log(reports[i].discussion_name)
                chunk+=fillreportlist('kongkongyzt',reports[i].discussion_name,reports[i].discussion_content,reports[i].discussion_time)
             }
             $('.main_content').html(goupid+chunk+commentHTML)
        }
    )
}

var filltasklist=function(projectname,taskname,taskdetail,deadline){

    var tasklist="<div class='col-md-12 '><div class='col-md-12 project_item'><div class='col-md-9 '><div class='col-md-12 '><div class='item_team_leader_name'>"+projectname+"</div><a class='item_project_name' href='project_task_detail.html'>"+taskname+"</a><div class='item_project_detail'>"+taskdetail+"</div></div></div><div class='col-md-3  distribution text-right'><div class='distribution_endTime'>截止日期："+deadline+"</div><div class='distribution_choice'><a class='btn btn-sm btn-default' href='#distribution_task' data-toggle='modal'>分配给...</a></div></div></div><div class='division'></div></div>"    

    return tasklist
}

var fillreportlist=function(author,title,content,time){
    var reportlist="<div class='division'></div><div class='discussion_list_wrapper'><div class='first_floor_detail'><div class='discussion_list_icon'><a href='#'><img class='file_head_icon' src='img/pic_head.png'></a><div class='first_floor_header'><span class='ds_user_name'>"+author+"</span></div></div><div class='report_list_content_wrapper'><div class = 'report_title_wrapper'><div class = 'discussion_list_title'><a class = 'discussion_list_href' href='#'>"+title+"</a></div><div class = 'report_list_upload_time'><a class = 'discussion_list_href' href='#'>2014-7-11</a></div></div><div class = 'report_list_content'><a class = 'discussion_list_href' href='#'>"+content+"</a></div><div class='first_floor_actions'><span class='ds-time'>"+time+"</span><a class='ds-post-reply' href='#'>回复</a><a class='ds-post-reply' href='#'>赞</a><a class='ds-post-reply' href='#'> <i class ='icon_like'></i>标记一下</a><a class='ds-post-reply' href='#'>删除</a></div></div></div></div>"
    return reportlist
}

var fillgrouplist=function(name,detail,date){
    var grouplist="<div class='project_item_division'></div><div class='col-md-12  project_item'><div class='col-md-4 '><div class='col-md-1  project_item_checkbox'><input type='checkbox' /></div><div class='col-md-11  pull-right'><div class='item_team_leader_name'>组名</div><div class='item_project_name'>"+name+"</div><div class='item_project_detail'>"+detail+"</div></div></div><div class='col-md-4  project_update_time'>"+date+"</div><div class='col-md-4  project_deadline'>"+date+"</div></div>"
    
    return grouplist
}

var addReport=function(){
    $.post('discussionAction!addReport.do',
        {
           group_id : $('#groupid').val(),
           discussionName:$('#report-title').val(),
           discussionContent:$('#report-content').val()
        },
        function(data,status){
            var handler=new Date()
            var now=handler.getFullYear()+"-"+handler.getDate()+"-"+handler.getMinutes()
            var result=fillreportlist('kongkongyzt',$('#report-title').val(),$('#report-content').val(),now)
            $('#hook').html(result)
        }
    )
}

function test(){
    var handler=new Date()
    var now=handler.getFullYear()+"-"+handler.getDate()+"-"+handler.getMinutes()
    var result=fillreportlist('kongkongyzt',$('#report-title').val(),$('#report-content').val(),now)
    $('#hook').html(result)
    //console.log(result)
}

var getProjectList=function(){
    $.post('iprojectAction.do'+location.search,
        {
            
        },
        function(data,status){
             var response=JSON.parse(data)
             if(response['content']=='list'){
                 var groups=response['groups']
                 var chunk=''
                 for(var i=0;i<groups.length;i++){
                    chunk+=fillProjectList(groups[i].user_name,groups[i].group_id,groups[i].group_name,groups[i].group_discription,groups[i].group_date)
                 }
                 $('#project-list').html(chunk)
             }else{
                $('#if-detail').html("<div class='division'></div><div class='col-md-12 tag_bar'><ul class='nav nav-tabs'><li><a href='javascript:void(0)' onclick=project_init('task')>任务</a></li><li><a href='javascript:void(0)' onclick=project_init('discussion')>讨论</a></li></ul></div>")
             }
        }
    )
}

var getTeamInfo=function(){
    $.post('imemberAction.do',
        {
            
        },
        function(data,status){
             var response=JSON.parse(data)
             if(response['type']=='member'){
             var manager=response['manager']['user_name']
             var members=response['members']
             var chunk=''
             var teamcheif
             $('#manager').html(manager)
             for(var i=0;i<members.length;i++){
                if(members[i].user_role==2){
                    $('#teamcheif').html(members[i].user_name)
                }else{
                    chunk+=fillteam(members[i].user_name)
                }
             }
             //console.log(chunk)
             $('#teammemberlist').html(chunk)}
             else{
                var manager=response[0]['manager']['user_name']
             var members=response[0]['members']
             var chunk=''
             var teamcheif
             $('#manager').html(manager)
             for(var i=0;i<members.length;i++){
                if(members[i].user_role==2){
                    $('#teamcheif').html(members[i].user_name)
                }else{
                    chunk+=fillteam(members[i].user_name)
                }
             }
             //console.log(chunk)
             $('#teammemberlist').html(chunk)
             }
        }
    )
}

var fillteam=function(username){
    var html="<a class='team_head_container'><div class='team_head_img'><img src='img/member_head.png'/></div><div class='team_member_name'>"+username+"</div></a>"
    return html
}
var fillProjectList=function(teamchief,group_id,projectName,detail,group_date){
    var html="<div class='project_item_division'></div><div class='col-md-12  project_item'><div class='col-md-4 '><div class='col-md-1  project_item_checkbox'><input type='checkbox' /></div><div class='col-md-11  pull-right'><div class='item_team_leader_name'>"+teamchief+"</div><div class='item_project_name'><a href='project.html?groupid="+group_id+"'>"+projectName+"</a></div><div class='item_project_detail'>"+detail+"</div></div></div><div class='col-md-4  project_update_time'>"+group_date+"</div><div class='col-md-4  project_deadline'>2014-05-02</div></div>"
    return html
}
var project_init=function(type){
    if(type=='task'){
        $.post('iprojectAction.do'+location.search,
        {
            
        },
        function(data,status){
             var response=JSON.parse(data)
             var tasks=response['tasks']
             var chunk=''
             for(var i=0;i<tasks.length;i++){
                chunk+=filltasklist(tasks[i].group_name,tasks[i].task_name,tasks[i].task_deadline)
             }
             $('#main_content').html(chunk)
        }
    )
    }else if(type=='discussion'){
        $.post('iprojectAction.do'+location.search,
        {
            
        },
        function(data,status){
             var response=JSON.parse(data)
             var discussions=response['discussions']
             var chunk=''
             for(var i=0;i<discussions.length;i++){
                chunk+=chunk+=fillreportlist('kongkongyzt',discussions[i].discussion_name,discussions[i].discussion_content,discussions[i].discussion_time)
             }
             $('#main_content').html(chunk)
        }
        )
    }else{
        alert('unknown action')
    }
}

getuserInfo()

