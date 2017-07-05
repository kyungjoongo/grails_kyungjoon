<!doctype html>
<html>
<head>
</head>
<body>

<table width="1500px" border="1">

    <g:each var="testOne" in="${testList}">

        <tr>
            <td>${testOne.content}</td>
            <td>${testOne.name}</td>
            <td>${testOne.pubDate}</td>
        </tr>



    </g:each>



%{--

    <img src="${request.contextPath}/TestController/getImage" alt="whatever. alternatively, filename"/>
--}%


    <img src="/test/getImage?name=Jellyfish.jpg" width="50%" height="50%"/>

</table>

    </body>
    </html>
