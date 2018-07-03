function showDialogWaiting(){
    var dialog = document.querySelector('dialog');
    dialog.showModal();
}
function showDialog(title, description, countOfButtons, titleButton1, titleButton2, makeFunction){
    var dialog = document.querySelector('dialog');
    dialog.close();

    var htmlButtons ='      <button type="button" class="mdl-button button1">'+titleButton1+'</button>\n' +
        '      <button type="button" class="mdl-button button2">'+titleButton2+'</button>\n';

    if(countOfButtons == 1){
        htmlButtons ='<button type="button" class="mdl-button button1">'+titleButton1+'</button>\n';
    }

    var functionButton = '';
    if(makeFunction == "singleButtonAccept") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    if(makeFunction == "goToActivate") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '      window.location.replace("/activate");\n' +
            '    });\n';
    }

    if(makeFunction == "goToLogin") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '      window.location.replace("/");\n' +
            '    });\n';
    }

    if(makeFunction == "goToIndex") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '      window.location.replace("/");\n' +
            '    });\n';
    }

    if(makeFunction == "deleteList") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '    $.ajax({\n' +
            '        type: \'POST\',\n' +
            '        url: \'/list/delete/\'+$("#listId").val(),\n' +
            '        complete: function (response) {\n' +
            '           var jsonResponse = JSON.parse(response.responseText);'+
            '            dialog.close();\n' +
            '            if(jsonResponse.error == \'1\'){\n' +
            '                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");\n' +
            '            }else{'+
            '               getUpdateLists();\n' +
            '           }\n' +
            '        }\n' +
            '    });'+
            '    });\n';

        functionButton+='dialog.querySelector(\'.button2\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    if(makeFunction == "changeNameList") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '    $.ajax({\n' +
            '        type: \'POST\',\n' +
            '        url: \'/list/edit\',\n' +
            '        data: {\'id\': $("#listId").val(), \'newListName\': $("#newListName").val(), \'newListColour\': \'008b8b\'},' +
            '        complete: function (response) {\n' +
            '           var jsonResponse = JSON.parse(response.responseText);'+
            '            dialog.close();\n' +
            '            if(jsonResponse.error == \'1\'){\n' +
            '                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");\n' +
            '            }else{'+
            '               getUpdateLists();\n' +
            '           }\n' +
            '        }\n' +
            '    });'+
            '    });\n';

        functionButton+='dialog.querySelector(\'.button2\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    if(makeFunction == "addNewList") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '    $.ajax({\n' +
            '        type: \'POST\',\n' +
            '        url: \'/list/add\',\n' +
            '        data: {\'listName\': $("#newListName").val(), \'listColour\':\'008b8b\', \'numOrder\': \'1\', \'showed\': \'1\'},' +
            '        complete: function (response) {\n' +
            '           var jsonResponse = JSON.parse(response.responseText);'+
            '            dialog.close();\n' +
            '            if(jsonResponse.error == \'1\'){\n' +
            '                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");\n' +
            '            }else{'+
            '               getUpdateLists();\n' +
            '           }\n' +
            '        }\n' +
            '    });'+
            '    });\n';

        functionButton+='dialog.querySelector(\'.button2\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    if(makeFunction == "addNewTask") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '    $.ajax({\n' +
            '        type: \'POST\',\n' +
            '        url: \'/task/add\',\n' +
            '        data: {\'listId\': $("#listId").val(), \'name\':$("#newTaskName").val(), \'comment\': $("#newTaskComment").val(), ' +
            '               \'priority\': \'1\', \'dateTime\': $("#newTaskDate").val(), \'repeatTime\': \'1\', \'done\': \'0\'},' +
            '        complete: function (response) {\n' +
            '           var jsonResponse = JSON.parse(response.responseText);'+
            '            dialog.close();\n' +
            '            if(jsonResponse.error == \'1\'){\n' +
            '                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");\n' +
            '            }else{'+
            '               getUpdateLists();\n' +
            '           }\n' +
            '        }\n' +
            '    });'+
            '    });\n';

        functionButton+='dialog.querySelector(\'.button2\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    if(makeFunction == "deleteTask") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '    $.ajax({\n' +
            '        type: \'POST\',\n' +
            '        url: \'/task/delete/\'+$("#taskId").val(),\n' +
            '        complete: function (response) {\n' +
            '           var jsonResponse = JSON.parse(response.responseText);'+
            '            dialog.close();\n' +
            '            if(jsonResponse.error == \'1\'){\n' +
            '                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");\n' +
            '            }else{'+
            '               getUpdateLists();\n' +
            '           }\n' +
            '        }\n' +
            '    });'+
            '    });\n';

        functionButton+='dialog.querySelector(\'.button2\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    if(makeFunction == "editTask") {
        functionButton = 'dialog.querySelector(\'.button1\').addEventListener(\'click\', function() {\n' +
            '    $.ajax({\n' +
            '        type: \'POST\',\n' +
            '        url: \'/task/edit\',\n' +
            '        data: {\'id\': $("#taskId").val(), \'newTaskListId\': $("#newTaskListId").val(), \'newTaskName\': $("#newTaskName").val(),' +
            '               \'newTaskComment\': $("#newTaskComment").val(), \'newTaskPriority\': \'1\', \'newTaskDate\': $("#newTaskDate").val(),' +
            '               \'newTaskRepeatTime\': \'1\',\'newTaskDone\': \'0\'},' +
            '        complete: function (response) {\n' +
            '           var jsonResponse = JSON.parse(response.responseText);'+
            '            dialog.close();\n' +
            '            if(jsonResponse.error == \'1\'){\n' +
            '                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");\n' +
            '            }else{'+
            '               getUpdateLists();\n' +
            '           }\n' +
            '        }\n' +
            '    });'+
            '    });\n';

        functionButton+='dialog.querySelector(\'.button2\').addEventListener(\'click\', function() {\n' +
            '$("#message").html(""); ' +
            '      dialog.close();\n' +
            '    });\n';
    }

    $('#message').html('<dialog class="mdl-dialog">\n' +
        '    <h4 class="mdl-dialog__title">'+ title +'</h4>\n' +
        '    <div class="mdl-dialog__content">\n' +
        '      <p>\n' + description +
        '      </p>\n' +
        '    </div>\n' +
        '    <div class="mdl-dialog__actions">\n' +
        htmlButtons +
        '    </div>\n' +
        '  </dialog>\n' +
        '  <script>\n' +
        '    var dialog = document.querySelector(\'dialog\');\n' +
        '    if (! dialog.showModal) {\n' +
        '      dialogPolyfill.registerDialog(dialog);\n' +
        '    }\n' +
        '      dialog.showModal();\n' +
        functionButton +
        '  </script>');
}

function login(){
    showDialogWaiting();
    if($('#usernameLogin').val() == "" || $('#passwordLogin').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/loginProcess',
        data: {'username': $('#usernameLogin').val(),
            'password': $('#passwordLogin').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                window.location.replace("/");
            }
        }
    });

}

function register(){
    showDialogWaiting();
    if($('#username').val() == "" || $('#email').val() == "" || $('#password').val() == "" || $('#firstName').val() == "" || $('#secondName').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/registerProcess',
        data: {'username': $('#username').val(),
            'email': $('#email').val(),
            'password': $('#password').val(),
            'firstName': $('#firstName').val(),
            'secondName': $('#secondName').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                showDialog("Account created!", "Your account has created! Check your email for activate account!", 1, "Close", "Close", "goToActivate");
            }
        }
    });

}

function resendActivateCode(){
    showDialogWaiting();
    if($('#email').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/resendActivateCode',
        data: {'email': $('#email').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                showDialog("Mail sent!", "Check your email! New activation code was sent!", 1, "Close", "Close", "goToActivate");
            }
        }
    });

}

function activateAccount(){
    showDialogWaiting();
    if($('#activationCode').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/activate',
        data: {'activationCode': $('#activationCode').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                showDialog("Account activated!", "Your account has activated! Try to login!", 1, "Close", "Close", "goToLogin");
            }
        }
    });

}

function rememberPassword(){
    showDialogWaiting();
    if($('#email').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/sendResetPassword',
        data: {'email': $('#email').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                showDialog("Email has sent!", "Check your email! Reset Password Code has sent!", 1, "Close", "Close", "goToLogin");
            }
        }
    });
}

function resetPassword(){
    showDialogWaiting();
    if($('#password').val() == "" || $('#code').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/resetPassword',
        data: {'code': $('#code').val(),
            'password': $('#password').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                showDialog("Password has reset!", "Try to login using new password!", 1, "Close", "Close", "goToLogin");
            }
        }
    });

}

function editSettings(){
    showDialogWaiting();
    if($('#oldPassword').val() == "" || $('#firstName').val() == "" || $('#secondName').val() == "" ||
        $('#email').val() == "") {
        showDialog("Empty inputs!", "You have to fill all inputs!", 1, "Close", "Close", "singleButtonAccept");
        return false;
    }

    $.ajax({
        type: 'POST',
        url: '/editAccount',
        data: {'oldPassword': $('#oldPassword').val(),
            'firstName': $('#firstName').val(),
            'secondName': $('#secondName').val(),
            'email': $('#email').val(),
            'password': $('#password').val()},
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                showDialog("Settings changed!", "Your settings has changed!", 1, "Close", "Close", "goToIndex");
            }
        }
    });

}


function changeShowList(id) {
    if($('#listBody_'+id+'').css('display') == "block"){
        $('#listBody_'+id+'').css('display', 'none');
        $('#listBar_'+id+'').css('height', '30px');
    }
    else{
        $('#listBody_'+id+'').css('display', 'block');
        $('#listBar_'+id+'').css('height', '300px');
    }

    $.ajax({
        type: 'POST',
        url: '/list/changeShowed/'+id,
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
        }
    });

}

function deleteList(id, name){
    var invisible = "<input id='listId' type='hidden' value='"+id+"' />";
    showDialog("Delete list?", "Are you sure to delete list: <b>"+ name +"</b>?" + invisible, 2, "Delete", "Close", "deleteList");
}

function changeNameList(id){
    showDialogWaiting();
    var invisible = "<input id='listId' type='hidden' value='"+id+"' />";

    $.ajax({
        type: 'GET',
        url: '/list/getList/'+id,
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);

            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                var inputName = "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
                    "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"newListName\" value='"+jsonResponse.value.name+"'>\n" +
                    "  </div>";
                showDialog("New name", "Write new name and click Change.<br>"+ inputName + invisible, 2, "Change", "Close", "changeNameList");
            }

        }
    });
}

function addNewList(){
    var form = "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
        "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"newListName\">\n" +
        "  </div>";
    showDialog("New List", "Give new for list and click Add<br>"+form, 2, "Add", "Close", "addNewList");
}

function addNewTask(id){
    var invisible = "<input id='listId' type='hidden' value='"+id+"' />";

    var form = "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
        "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"newTaskName\" placeholder=\"Name\">\n" +
        "  </div>";

    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
        "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"newTaskComment\" placeholder=\"Comment\">\n" +
        "  </div>";

    var date = new Date();
    var month = "0" + (parseInt(date.getMonth())+1);
    var day = "0" + date.getDate();

    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
        "    <input class=\"mdl-textfield__input\" type=\"datetime-local\" id=\"newTaskDate\" " +
        "value=\""+date.getFullYear()+"-"+month.substr(-2)+"-"+day.substr(-2)+"T00:00\">\n" +
        "  </div>";

    showDialog("New Task", "Give parameters and click Add.<br>"+form+invisible, 2, "Add", "Close", "addNewTask");
}

function deleteTask(id, name){
    var invisible = "<input id='taskId' type='hidden' value='"+id+"' />";
    showDialog("Delete task?", "Are you sure to delete task: <b>"+ name +"</b>?" + invisible, 2, "Delete", "Close", "deleteTask");
}

function doTask(id){
    showDialogWaiting();
    $.ajax({
        type: 'POST',
        url: '/task/do/'+id,
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);

            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                getUpdateLists();
            }

        }
    });
}

function editTask(id){
    showDialogWaiting();

    var invisible = "<input id='taskId' type='hidden' value='"+id+"' />";

    $.ajax({
        type: 'GET',
        url: '/task/getTask/'+id,
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                $.ajax({
                    type: 'GET',
                    url: '/list/getLists',
                    complete: function (response) {
                        var jsonResponseTask = JSON.parse(response.responseText);
                        if(jsonResponseTask.error == '1'){
                            showDialog(jsonResponseTask.errorTitle, jsonResponseTask.errorDescription, 1, "Close", "Close", "singleButtonAccept");
                        }else{
                            var form = "<select class=\"mdl-textfield__input\" id='newTaskListId'>";
                            var countResponse = jsonResponseTask.value.length;
                            jsonResponseTask.value.forEach(function(list) {
                                var selected = "";
                                if(list.id == jsonResponse.value.listId){
                                    selected = "selected";
                                }

                                form+="<option value='"+list.id+"' "+selected+">"+list.name+"</option>";
                                countResponse--;
                            });

                            function showRestEditTask() {
                                if(countResponse != 0){
                                    setTimeout(function () {
                                        showRestEditTask();
                                    }, 5);
                                }else {
                                    form += "</select>";

                                    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
                                        "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"newTaskName\" placeholder=\"Name\" value='" + jsonResponse.value.name + "'>\n" +
                                        "  </div>";

                                    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
                                        "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"newTaskComment\" placeholder=\"Comment\" value='" + jsonResponse.value.comment + "'>\n" +
                                        "  </div>";

                                    var date = new Date(jsonResponse.value.date);
                                    var year = date.getFullYear();
                                    var month = "0" + (parseInt(date.getMonth())+1);
                                    var day = "0" + date.getDate();
                                    var hours = "0" + date.getHours();
                                    var minutes = "0" + date.getMinutes();

                                    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
                                        "    <input class=\"mdl-textfield__input\" type=\"datetime-local\" id=\"newTaskDate\" " +
                                        "           value='"+year+"-"+month.substr(-2)+"-"+day.substr(-2)+"T"+hours.substr(-2)+":"+minutes.substr(-2)+"'>\n" +
                                        "  </div>";

                                    showDialog("Edit task", "Give new parameters and click Edit.<br>" + form + invisible, 2, "Edit", "Close", "editTask")
                                }
                            }

                            setTimeout(function () {
                                showRestEditTask();
                            }, 1);
                        }

                    }
                });
            }

        }
    });
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev, listId) {
    ev.dataTransfer.setData("listId", listId);
}

function drop(ev, numOrder) {
    ev.preventDefault();
    var listId = ev.dataTransfer.getData("listId");

    changeNumOrder(listId, numOrder);
}

function changeNumOrder(listId, numOrder){
    showDialogWaiting();
    $.ajax({
        type: 'POST',
        url: '/list/changeNumOrder/'+listId+'/'+numOrder,
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                getUpdateLists();
            }
        }
    });
}

function getUpdateLists(){
    var dialog = document.querySelector('dialog');
    dialog.close();
    showDialogWaiting();
    $.ajax({
        type: 'GET',
        url: '/getLists',
        complete: function (response) {
            var jsonResponse = JSON.parse(response.responseText);
            if(jsonResponse.error == '1'){
                showDialog(jsonResponse.errorTitle, jsonResponse.errorDescription, 1, "Close", "Close", "singleButtonAccept");
            }else{
                var listsBody = "";

                var countResponse = jsonResponse.value.length;

                jsonResponse.value.forEach(function(list) {
                    var listBarHeight = "300";
                    var listBodyVisible = "display: block;";
                    if(!list.showed){
                        listBarHeight = "30";
                        listBodyVisible = "display: none;";
                    }

                    listsBody+='<div draggable="true" ondragstart="drag(event, '+list.id+')" id="listBar_'+list.id+'" ondrop="drop(event, '+list.numOrder+')" ondragover="allowDrop(event)" style="display: inline-block;width: 250px; height: '+listBarHeight+'px; background-color: #008b8b; margin: 1px; margin-bottom: 5px; vertical-align:top; color: #FFF;">\n';
                    listsBody+='<div style="width: 100%; height: 30px;  border-bottom: 2px solid #333333; background-color: #006F6F;">';
                    listsBody+='<table style="width: 100%; text-align: center;line-height: 30px;">';
                    listsBody+='<tr>';
                    listsBody+='<td>#'+list.numOrder+'</td>';
                    listsBody+='<td onclick="changeNameList('+list.id+')" style="width: 70%;">'+list.name+'</td>';
                    listsBody+='<td style="line-height: 0;">';
                    listsBody+='<button title="Change show" onclick="changeShowList('+list.id+')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 0 0 0 2px;margin: 0;"\n' +
                        'class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                        '-\n' +
                        '</button>\n';
                    listsBody+='</td>';
                    listsBody+='<td style="line-height: 0;">';
                    listsBody+='<button title="Delete list" onclick="deleteList('+list.id+', \''+list.name+'\')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                        'class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                        'X\n' +
                        '</button>';
                    listsBody+='</td>';
                    listsBody+='</tr>';
                    listsBody+='</table>';
                    listsBody+='</div>';
                    listsBody+='<div id="listBody_'+list.id+'" style="'+listBodyVisible+' width: 100%; height: 270px; background-color: #008b8b;overflow-y: auto;overflow-x: hidden;">';

                    var iterationColor = 1;
                    list.tasks.forEach(function(task) {
                        if(!task.done) {
                            var color = "#008b8b";
                            if(iterationColor % 2 == 0){
                                color = "#1B9A9A";
                            }
                            iterationColor += 1;

                            listsBody += '<table style="table-layout: fixed; width: 100%; border-bottom: 1px solid #333333; background-color: '+color+';">';
                            listsBody += '<tr>';
                            listsBody += '<td style="width: 67%; word-wrap:break-word;"><b>'+task.name+'</b></td>';
                            listsBody += '<td style="width: 11%; line-height: 0;" rowspan="2">';
                            listsBody += '<button title="Edit task" onclick="editTask('+task.id+')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                                'class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                                'E\n' +
                                '</button>';
                            listsBody += '</td>';
                            listsBody += ' <td style="width: 11%; line-height: 0;" rowspan="2">';
                            listsBody += '<button title="Delete task" onclick="deleteTask('+task.id+', \''+task.name+'\')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                                'class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                                'X\n' +
                                '</button>';
                            listsBody += '</td>';
                            listsBody += '<td style="width: 11%; line-height: 0;" rowspan="2">';
                            listsBody += '<button title="Do task" onclick="doTask('+task.id+')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                                'class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">\n' +
                                'D\n' +
                                '</button>';
                            listsBody += '</td>';
                            listsBody += '</tr>';

                            var date = new Date(task.date);
                            var year = date.getFullYear();
                            var month = "0" + (parseInt(date.getMonth())+1);
                            var day = "0" + date.getDate();
                            var hours = "0" + date.getHours();
                            var minutes = "0" + date.getMinutes();

                            listsBody += '<td style="font-size: 11px;">'+year+'-'+month.substr(-2)+'-'+day.substr(-2)+' '+hours.substr(-2)+':'+minutes.substr(-2)+'</td>';
                            listsBody += '<td colspan="3"></td>';
                            listsBody += '</tr>';
                            listsBody += '<tr>';
                            listsBody += '<td style="word-wrap:break-word; font-size: 10px;" colspan="4">'+task.comment+'</td>';
                            listsBody += '</tr>';
                            listsBody += '</table>';
                        }
                    });

                    listsBody += '<table style="width: 100%; text-align: center;">';
                    listsBody += '<tr>';
                    listsBody += '<td>';
                    listsBody += '<button onclick="addNewTask('+list.id+')" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                        'Add new task' +
                        '</button>';
                    listsBody += '</td>';
                    listsBody += '</tr>';
                    listsBody += '</table>';
                    listsBody += '</div>';
                    listsBody += '</div>';

                    countResponse--;
                });


                var listsHistoryBody = "";

                var countHistoryResponse = jsonResponse.value.length;

                jsonResponse.value.forEach(function(list) {
                    listsHistoryBody+='<div draggable="true" ondragstart="drag(event, '+list.id+')" id="listBar_'+list.id+'" ondrop="drop(event, '+list.numOrder+')" ondragover="allowDrop(event)" id="listBar_'+list.id+'" style="display: inline-block;width: 250px; height: 300px; background-color: #008b8b; margin: 1px; margin-bottom: 5px; vertical-align:top; color: #FFF;">\n';
                    listsHistoryBody+='<div style="width: 100%; height: 30px;  border-bottom: 2px solid #333333; background-color: #006F6F;">';
                    listsHistoryBody+='<table style="width: 100%; text-align: center;line-height: 30px;">';
                    listsHistoryBody+='<tr>';
                    listsHistoryBody+='<td>#'+list.numOrder+'</td>';
                    listsHistoryBody+='<td style="width: 80%;" onclick="changeNameList('+list.id+')" style="width: 70%;">'+list.name+'</td>';
                    listsHistoryBody+='<td style="line-height: 0;">';
                    listsHistoryBody+='<button title="Delete list" onclick="deleteList('+list.id+', \''+list.name+'\')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                        'class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                        'X\n' +
                        '</button>';
                    listsHistoryBody+='</td>';
                    listsHistoryBody+='</tr>';
                    listsHistoryBody+='</table>';
                    listsHistoryBody+='</div>';
                    listsHistoryBody+='<div id="listBody_'+list.id+'" style="width: 100%; height: 270px; background-color: #008b8b;overflow-y: auto;overflow-x: hidden;">';
                    var iterationColor = 1;
                    list.tasks.forEach(function(task) {
                            if(task.done){
                                var color = "#008b8b";
                                if(iterationColor % 2 == 0){
                                    color = "#1B9A9A";
                                }
                                iterationColor+=1;

                                listsHistoryBody+=' <table style="table-layout: fixed; width: 100%; border-bottom: 1px solid #333333; background-color: '+color+';">';
                                listsHistoryBody+='<tr>';
                                listsHistoryBody+='<td style="width: 76%;word-wrap:break-word;"><b>'+task.name+'</b></td>';
                                listsHistoryBody+='<td style="width: 12%;line-height: 0;" rowspan="2">';
                                listsHistoryBody+=' <button title="Delete task" onclick="deleteTask('+task.id+', \''+task.name+'\')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                                    'class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                                    'X\n' +
                                    '</button>';
                                listsHistoryBody+='</td>';
                                listsHistoryBody+='<td style="width: 12%;line-height: 0;" rowspan="2">';
                                listsHistoryBody+='<button title="Undo task" onclick="doTask('+task.id+')" style="min-width: 25px; max-width: 25px; height: 25px; line-height: 25px; padding: 1px 0 0 2px;margin: 0;"\n' +
                                    'class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect">\n' +
                                    'U\n' +
                                    '</button>';
                                listsHistoryBody+='</td>';
                                listsHistoryBody+='</tr>';
                                listsHistoryBody+='<tr>';

                                var date = new Date(task.date);
                                var year = date.getFullYear();
                                var month = "0" + (parseInt(date.getMonth())+1);
                                var day = "0" + date.getDate();
                                var hours = "0" + date.getHours();
                                var minutes = "0" + date.getMinutes();

                                listsHistoryBody += '<td style="font-size: 11px;">'+year+'-'+month.substr(-2)+'-'+day.substr(-2)+' '+hours.substr(-2)+':'+minutes.substr(-2)+'</td>';
                                listsHistoryBody+='<td colspan="2"></td>';
                                listsHistoryBody+='</tr>';
                                listsHistoryBody+='<tr>';
                                listsHistoryBody+=' <td style="word-wrap:break-word; font-size: 10px;" colspan="3">'+task.comment+'</td>';
                                listsHistoryBody+='</tr>';
                                listsHistoryBody+='</table>';
                            }
                    });

                    listsHistoryBody+='</div>';
                    listsHistoryBody+='</div>';

                    countHistoryResponse--;

                });

                function showRestListBody() {
                    if(countResponse != 0 || countHistoryResponse != 0){
                        setTimeout(function () {
                            showRestListBody();
                        }, 5);
                    }else {
                        listsBody += '<div id="addList" style="display: inline-block;width: 250px; height: 40px; background-color: #009F5B; margin: 1px; vertical-align:top; color: #FFF;">';
                        listsBody += '<div style="width: 100%; height: 40px;">';
                        listsBody += '<button onclick="addNewList()" style="width: 100%; height: 100%;" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">\n' +
                            'Add New List\n' +
                            '</button>';
                        listsBody += '</div>';
                        listsBody += '</div>';

                        var dialog = document.querySelector('dialog');
                        dialog.close();

                        $("#listsBody").html(listsBody);
                        $("#listsHistoryBody").html(listsHistoryBody);
                    }
                }

                setTimeout(function () {
                    showRestListBody();
                }, 1);


            }
        }
    });
}