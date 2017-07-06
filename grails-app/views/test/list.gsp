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
            %{--<Td>${testOne.imageName}</Td>--}%
            <td>
                <img src="/test/getImage?name=${testOne.imageName}" style="width: 10%; height: 10%"/>
            </td>
        </tr>



    </g:each>



%{--

    <img src="${request.contextPath}/TestController/getImage" alt="whatever. alternatively, filename"/>
--}%


    %{--<img src="/test/getImage?name=Jellyfish.jpg" style="width: 10%; height: 10%"/>--}%

</table>

    </body>
    </html>
