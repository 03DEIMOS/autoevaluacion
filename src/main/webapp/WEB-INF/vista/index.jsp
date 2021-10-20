<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link href="css/layout-sitenav.css" type="text/css" rel="stylesheet" media="screen">
        <title>Autoevaluacion UTB</title>
        <link href="http://fonts.googleapis.com/css?family=Lobster|Oswald|Kaushan+Script" rel="stylesheet" type="text/css">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <!-- <link href="css/slider.css" rel="stylesheet" type="text/css" />-->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/otro.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css">
        <!-- Custom styles for this template -->
        <link href="css/jumbotron-narrow.css" rel="stylesheet">
        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

    </head>
    
   <%-- <body style="padding-top: 0px; background-image: url(img/UTB_1920x970.jpg); background-repeat: no-repeat;background-size: 100%;"> --%>
    <body style="background: url(img/UTB_1920x820.jpg) no-repeat center center fixed; 
          -webkit-background-size: cover;
          -moz-background-size: cover;
          -o-background-size: cover;
          background-size: cover;">
   

        <!--<div class="container">-->
        <div class="row" id="login" >
            <form name="formularioLogin" class="form-signin" id="formulario_login" action="<%=request.getContextPath()%>/" method="post" style="max-width: 700px;">
                <c:if test="${errorLogin == true}">
                    <div class="alert alert-error fade in" id="login-error">
                        <button type="button" class="close" id="close1">x</button>
                        <label generated='true' class='error'>${message}</label>
                    </div>
                </c:if>
                <!--<h2 class="form-signin-heading">Acceder</h2>-->
                <div style="text-align: center">
                    <input type="text" placeholder="Código" name="codigo" id="codigo" class="{required:true}">
                    <button id="btnIniciar" type="submit" class="btn btn-large btn-primary" style="margin-bottom: 15px;">Acceder</button>
                    <label style="color:white;">* Si es Directivo o Administrativo por favor ingrese con su número de cédula.</label>
                </div>
            </form>
        </div>

        <!--</div>-->

        <!-- /container -->

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/jquery.metadata.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/jquery.ba-hashchange.min.js"></script>
        <script type='text/javascript' src='js/slider.js'></script>
        <script src="js/main.js"></script>


    </body>
</html>
