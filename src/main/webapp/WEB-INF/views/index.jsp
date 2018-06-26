<!doctype html>
<html lang="en">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <jsp:useBean id="date" class="java.util.Date" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="TODOList">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>TODOList</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="style/images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="style/images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="style/images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="style/images/favicon.png">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-deep_purple.min.css">
    <link rel="stylesheet" href="style/styles.css">


    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <script src="style/jquery-3.3.1.min.js"></script>
    <!-- Custom Theme files -->
    <link href="style/cssCalendar/style.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <!--Calender-->
    <link rel="stylesheet" href="style/cssCalendar/clndr.css" type="text/css" />
    <script src="style/jsCalendar/underscore-min.js"></script>
    <script src= "style/jsCalendar/moment-2.2.1.js"></script>
    <script src="style/jsCalendar/clndr.js"></script>
    <script src="style/jsCalendar/site.js"></script>
    <script src="style/scripts.js"></script>

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

    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
    </style>
</head>
<body onload="startTime()" class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
<div id="message"></div>
<dialog id="messageWaiting" class="mdl-dialog">
    <h4 class="mdl-dialog__title">Progress...</h4>
    <div class="mdl-dialog__content">
        <div id="p2" class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div>
    </div>
</dialog>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
        <div style="display: block;" class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
            <a style="float: left;" href="#start" class="mdl-layout__tab is-active">Start</a>
            <a style="float: right;" href="/logout" class="mdl-layout__tab">Logout</a>
            <a style="float: right;" href="#settings" class="mdl-layout__tab">Settings</a>
            <p style="float: right; color: #FFF;" class="mdl-layout__tab">Hello, ${username}!</p>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="content">
            <div class="calender">
                <div class="cal1">
                    <div class="clndr">
                        <table class="clndr-table" border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr class="header-days">
                                <td class="header-day">S</td>
                                <td class="header-day">M</td>
                                <td class="header-day">T</td>
                                <td class="header-day">W</td>
                                <td class="header-day">T</td>
                                <td class="header-day">F</td>
                                <td class="header-day">S</td>
                            </tr>
                            </thead>

                            <tbody>

                            <tr>
                                <td class="day past calendar-day-2015-11-01">
                                    <div class="day-contents">01</div>
                                </td>
                                <td class="day past calendar-day-2015-11-02">
                                    <div class="day-contents">02</div>
                                </td>
                                <td class="day past calendar-day-2015-11-03">
                                    <div class="day-contents">03</div>
                                </td>
                                <td class="day past calendar-day-2015-11-04">
                                    <div class="day-contents">04</div>
                                </td>
                                <td class="day past calendar-day-2015-11-05">
                                    <div class="day-contents">05</div>
                                </td>
                                <td class="day past calendar-day-2015-11-06">
                                    <div class="day-contents">06</div>
                                </td>
                                <td class="day past calendar-day-2015-11-07">
                                    <div class="day-contents">07</div>
                                </td>
                            </tr>

                            <tr>
                                <td class="day past calendar-day-2015-11-08">
                                    <div class="day-contents">08</div>
                                </td>
                                <td class="day past calendar-day-2015-11-09">
                                    <div class="day-contents">09</div>
                                </td>
                                <td class="day past calendar-day-2015-11-10">
                                    <div class="day-contents">10</div>
                                </td>
                                <td class="day past calendar-day-2015-11-11">
                                    <div class="day-contents">11</div>
                                </td>
                                <td class="day past calendar-day-2015-11-12">
                                    <div class="day-contents">12</div>
                                </td>
                                <td class="day past calendar-day-2015-11-13">
                                    <div class="day-contents">13</div>
                                </td>
                                <td class="day past calendar-day-2015-11-14">
                                    <div class="day-contents">14</div>
                                </td>
                            </tr>

                            <tr>
                                <td class="day past calendar-day-2015-11-05">
                                    <div class="day-contents">15</div>
                                </td>
                                <td class="day past calendar-day-2015-11-16">
                                    <div class="day-contents">16</div>
                                </td>
                                <td class="day past calendar-day-2015-11-17">
                                    <div class="day-contents">17</div>
                                </td>
                                <td class="day past calendar-day-2015-11-18">
                                    <div class="day-contents">18</div>
                                </td>
                                <td class="day past calendar-day-2015-11-19">
                                    <div class="day-contents">19</div>
                                </td>
                                <td class="day past calendar-day-2015-11-20">
                                    <div class="day-contents">20</div>
                                </td>
                                <td class="day today calendar-day-2015-11-21">
                                    <div class="day-contents">21</div>
                                </td>
                            </tr>

                            <tr>
                                <td class="day calendar-day-2015-11-22">
                                    <div class="day-contents">22</div>
                                </td>
                                <td class="day calendar-day-2015-11-23">
                                    <div class="day-contents">23</div>
                                </td>
                                <td class="day calendar-day-2015-11-24">
                                    <div class="day-contents">24</div>
                                </td>
                                <td class="day calendar-day-2015-11-25">
                                    <div class="day-contents">25</div>
                                </td>
                                <td class="day calendar-day-2015-11-26">
                                    <div class="day-contents">26</div>
                                </td>
                                <td class="day calendar-day-2015-11-27">
                                    <div class="day-contents">27</div>
                                </td>
                                <td class="day calendar-day-2015-11-28">
                                    <div class="day-contents">28</div>
                                </td>
                            </tr>

                            <tr>
                                <td class="day calendar-day-2015-11-29">
                                    <div class="day-contents">29</div>
                                </td>
                                <td class="day calendar-day-2015-11-30">
                                    <div class="day-contents">30</div>
                                </td>
                                <td class="day adjacent-month next-month calendar-day-2015-12-01">
                                    <div class="day-contents">01</div>
                                </td>
                                <td class="day adjacent-month next-month calendar-day-2015-12-02">
                                    <div class="day-contents">02</div>
                                </td>
                                <td class="day adjacent-month next-month calendar-day-2015-12-03">
                                    <div class="day-contents">03</div>
                                </td>
                                <td class="day adjacent-month next-month calendar-day-2015-12-04">
                                    <div class="day-contents">04</div>
                                </td>
                                <td class="day adjacent-month next-month calendar-day-2015-12-05">
                                    <div class="day-contents">05</div>
                                </td>
                            </tr>

                            </tbody>

                        </table>

                    </div>
                </div>
            </div>
            <div class="date">
                <div id="txt"></div>

                <div class="dmy">
                    <script type="text/javascript">
                        var mydate=new Date()
                        var year=mydate.getFullYear()
                        if(year<1000)
                            year+=1900
                        var day=mydate.getDay()
                        var month=mydate.getMonth()
                        var daym=mydate.getDate()
                        if(daym<10)
                            daym="0"+daym
                        var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
                        var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
                        document.write(""+dayarray[day]+", "+montharray[month]+" "+daym+", "+year+"")
                    </script>
                </div>

                <h2>REMINDERS</h2>
                <ul class="reminder">
                    <c:forEach items="${remindersTasks}" var="remindersTask">
                        <li><fmt:formatDate value="${remindersTask.date}" pattern="HH:mm" /> - ${remindersTask.name}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </main>
    <main class="mdl-layout__content">
        <div style="background-color: #f5f5f5;" class="mdl-layout__tab-panel" id="settings">
            <section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
                <div class="mdl-card mdl-cell mdl-cell--12-col">
                    <div class="mdl-card__supporting-text">
                        <h4>Change Accounts Settings</h4>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="password" id="password">
                            <label class="mdl-textfield__label" for="password">New Password</label>
                        </div>
                        <br />
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="firstName" value="${firstName}">
                            <label class="mdl-textfield__label" for="firstName">First Name</label>
                        </div>
                        <br />
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="secondName" value="${secondName}">
                            <label class="mdl-textfield__label" for="secondName">Second Name</label>
                        </div>
                        <br />
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="email" value="${email}">
                            <label class="mdl-textfield__label" for="email">Email</label>
                        </div>
                        <br />
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="password" id="oldPassword">
                            <label class="mdl-textfield__label" for="oldPassword">Old password</label>
                        </div>
                        <br />
                        <button onclick="editSettings()" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                            Edit account
                        </button>
                    </div>
                </div>
            </section>
        </div>
    </main>
</div>
<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
