<!doctype html>
<html>
<head>
</head>
<body>

%{--
<form action="/board2/write" method="post" enctype="multipart/form-data">

    <input type="file" name="myFile" id="myFile"/>


    <table border="1" cellspacing="0" style="width: 1500px">

        <tr>
            <td>author</td>
            <td><input type="text" id="author" name="author"></td>

        </tr>
        <tr>
            <td>
                내용
            </td>
            <td>
                <g:textArea name="content" id="content">

                </g:textArea>
            </td>

        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="submit" value="submit" id="submit"></input>
            </td>

        </tr>
    </table>
</form>--}%


<g:uploadForm action="write">
    %{--String name
    String content
    String pubDate
    String modDate--}%
    name: <input type="text"  name="name">
    content: <input type="text" name="content">
    <input type="file" name="myFile" />
    <input type="submit" />
</g:uploadForm>

    </body>
    </html>
