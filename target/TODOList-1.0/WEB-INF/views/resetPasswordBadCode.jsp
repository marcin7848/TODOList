<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="TODOList">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>TODOList</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="${pageContext.request.contextPath}/style/images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/style/images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="style/images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/style/images/favicon.png">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-deep_purple.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/styles.css">


    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <script src="${pageContext.request.contextPath}/style/jsCalendar/jquery-1.11.1.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/style/cssCalendar/style.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <!--Calender-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/cssCalendar/clndr.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/style/jsCalendar/underscore-min.js"></script>
    <script src= "${pageContext.request.contextPath}/style/jsCalendar/moment-2.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/style/jsCalendar/clndr.js"></script>
    <script src="${pageContext.request.contextPath}/style/jsCalendar/site.js"></script>
    <script src="${pageContext.request.contextPath}/style/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/style/scripts.js"></script>
    <script>
        function startTime() {
            var today = new Date();
            var h = today.getHours();
            var m = today.getMinutes();
            var s = today.getSeconds();
            m = checkTime(m);
            s = checkTime(s);
            document.getElementById('txt').innerHTML =
                h + ":" + m + ":" + s;
            var t = setTimeout(startTime, 500);
        }
        function checkTime(i) {
            if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
            return i;
        }
    </script>

</head>
<body onload="startTime()" class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
        <div style="display: block;" class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
            <a style="float: left;" href="/" class="mdl-layout__tab">Start</a>
            <a style="float: right;" href="/" class="mdl-layout__tab">Login</a>
            <a style="float: right;" href="/" class="mdl-layout__tab">Register</a>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="mdl-layout__tab-panel is-active" id="resendAC">
            <section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
                <div class="mdl-card mdl-cell mdl-cell--12-col">
                    <div class="mdl-card__supporting-text">
                        <h4>Reset Password</h4>
                    </div>
                </div>
            </section>
        </div>
    </main>
</div>
<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<dialog class="mdl-dialog">
    <h4 class="mdl-dialog__title">Bad Code!</h4>
    <div class="mdl-dialog__content">
        <p>
            Your code is not correct! Try reset password again!
        </p>
    </div>
    <div class="mdl-dialog__actions">
        <button type="button" class="mdl-button close">Close</button>
    </div>
</dialog>
<script>
    var dialog = document.querySelector('dialog');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    dialog.showModal();
    dialog.querySelector('.close').addEventListener('click', function() {
        dialog.close();
        window.location.replace("/");
    });
</script>
</body>
</html>
