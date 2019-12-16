<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Tinder</title>

    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>

</head>

<body style="background-color: #f5f5f5;">
<form class="form-users" action="/users" method="post">
    <div class="col-4 offset-4">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img  src=${user.photoUrl} class="mx-auto"" img-fluid rounded-circle>
                        <h3 class="mb-0 text-truncated">${user.userName}  ${user.userSurname}</h3>
                        <br>
<#--                    </div>-->
<#--                    <input type="hidden" name="user_id" value=${user.userId}>-->
<#--                    <div class="col-12 col-lg-6">-->
<#--                        <button name="dislike" type="submit" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span>-->
<#--                            Dislike-->
<#--                        </button>-->
<#--                    </div>-->
<#--                    <div class="col-12 col-lg-6">-->

<#--                        <button name="like" type="submit" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span>-->
<#--                            Like-->
<#--                        </button>-->
<#--                    </div>-->
                        <div class="col-12 col-lg-6">
                            <form action="users" method="post">
                                <input type="hidden" name="login" value="${user.userId}">
                                <button type="submit" class="btn btn-outline-danger btn-block" name="choice" value="NO"><span
                                            class="fa fa-times"></span>Dislike
                                </button>
                            </form>
                        </div>
                        <div class="col-12 col-lg-6">
                            <form action="users" method="post">
                                <input type="hidden" name="login" value="${user.userId}">
                                <button type="submit" class="btn btn-outline-success btn-block" name="choice" value="YES"><span
                                            class="fa fa-heart"></span>Like
                                </button>
                            </form>
                        </div>
                    <!--/col-->
                </div>
                <!--/row-->
            </div>
            <!--/card-block-->
        </div>
        <div style="display: flex; justify-content: space-between; margin-top: 20px;">
            <a href="/liked" role="button" class="btn btn-primary" style="display: block;width:130px;">Liked</a>
            <a href="/logout" role="button" class="btn btn-danger" style="display: block;width:130px;">Log out</a>
        </div>
    </div>
</form>
</body>
</html>