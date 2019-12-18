<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="/static/css/font.css">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/style.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body style="overflow-y: scroll">
<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <#--                        <i class="fa fa-comment"></i>-->
                        <h6 class="ml-1 mb-0">

                            <img src=${receiverId.photoUrl} class="mx-auto"" img-fluid rounded-circle
                                 style="width:150px;height:100px;">
                            <br>
                            <strong>${receiverId.userName}</strong>
                        </h6>
                    </div>

                </div>

            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul>
                        <#list messages as message>
                            <#if message.receiverId = receiverId.userId>
                                <li class="send-msg float-right mb-2">
                                    <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                        ${message.text}
                                    </p>
                                </li>
                            <#else>
                                <li class="receive-msg float-left mb-2">
                                    <div class="sender-img">
                                        <img src="${receiverId.photoUrl}" class="float-left">
                                    </div>
                                    <div class="receive-msg-desc float-left ml-2">
                                        <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                            ${message.text}
                                        </p>
<#--                                        <span class="receive-msg-time">ketty, Jan 25, 6:20 PM</span>-->
                                    </div>
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <form method="post">
                                <label>
                                    <input type="text" style="visibility: visible" name="message" class="border-0"
                                           placeholder="Send message"/>
                                </label>
                                <button type="submit" name="send"><span>Send</span></button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>