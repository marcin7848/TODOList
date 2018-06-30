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
            '               window.location.replace("/");\n' +
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
            '               window.location.replace("/");\n' +
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
            '               window.location.replace("/");\n' +
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
            '               window.location.replace("/");\n' +
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
            '               window.location.replace("/");\n' +
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
            '               window.location.replace("/");\n' +
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

    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
        "    <input class=\"mdl-textfield__input\" type=\"datetime-local\" id=\"newTaskDate\" " +
        "value=\""+date.getFullYear()+"-"+month.substr(-2)+"-"+date.getDate()+"T00:00\">\n" +
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
                window.location.replace("/");
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
                                    var day = date.getDate();
                                    var hours = "0" + date.getHours();
                                    var minutes = "0" + date.getMinutes();

                                    form += "  <div class=\"mdl-textfield mdl-js-textfield mdl-textfield--floating-label\">\n" +
                                        "    <input class=\"mdl-textfield__input\" type=\"datetime-local\" id=\"newTaskDate\" " +
                                        "           value='"+year+"-"+month.substr(-2)+"-"+day+"T"+hours.substr(-2)+":"+minutes.substr(-2)+"'>\n" +
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
                window.location.replace("/");
            }
        }
    });
}