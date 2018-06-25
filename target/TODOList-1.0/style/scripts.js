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