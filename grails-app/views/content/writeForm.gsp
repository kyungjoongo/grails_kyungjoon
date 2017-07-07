<!doctype html>
<html>
<head>

    <g:render template="/common/common" />
</head>
<body>
<g:uploadForm action="write">
    %{--String name
    String content
    String pubDate
    String modDate--}%
    <table class="table">
        <tr>
            <td>
                name: <input type="text"  name="name"><br/>
            </td>
        </tr>
        <tr>
            <td>
                content: <input type="text" name="content"><br/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="file" name="myFile" /><br/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" />

            </td>

        </tr>

    </table>
</g:uploadForm>

    </body>
    </html>
