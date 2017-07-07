<!doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<g:render template="/common/common" />
<body>

<script>

    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-full-width",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "1500",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }

    $(document).ready(function () {
        console.log("ready!");

        $('.comment').keypress(function (e) {
            var key = e.which;
            if(key == 13)  // the enter key code
            {
                /*alert('slkfsldkflskdf');*/

                $(".btnWrite").trigger("click");
            }
        });

        $(".btnWrite").on("click", function () {

            var commentEl = $(this).prev();
            var _comment = $(this).prev('.comment').val();
            var _testId = $(this).prev().prev('.testId').val();
            /*
             alert(_testId);*/

            $.ajax({
                url: '/content/writeComment',
                type: 'POST',
                data: {
                    comment: _comment,
                    _testId: $(this).prev().prev('.testId').val()
                },
                dataType: 'json',
                success: function (data) {
                    var author = data.result.author;
                    var content = data.result.content;
                    var theTemplateScript = $("#commentTmpl").html();
                    var theTemplate = Handlebars.compile(theTemplateScript);
                    // Pass our data to the template
                    var theCompiledHtml = theTemplate(data.result);
                    // Add the compiled html to the page
                    $("#commentList" + _testId).append(theCompiledHtml);
                    commentEl.val('');

                },
                error: function () {
                    alert("Sdfldsflk-->fail");
                    /*var _html = "<tr><td>" + author + "</td><td>" + content + "</td></tr>";
                     $("#commentList" + _testId).append(_html);*/
                }
            });

        });

        $(".mainImage").on("click", function () {

            /*toastr.options.timeOut = 5;*/


            toastr.success('좋아요!!!', '좋아요!');
           /*alert("좋아요~");*/

        });
    });

</script>

<div class="container">

    <table class="table" width="300px" border="0">
            %{--<colgroup>
                <col width="50px">
                <col width="150px">
                <col width="*">
            </colgroup>--}%
        <g:each var="testOne" in="${testList}">
            <tr>
                <td style="width: 80px">${testOne.id}</td>
                <td width="150px" style="background-color: whitesmoke">${testOne.name}</td>
                <td>${testOne.content}</td>

            </tr>
            <tr>
                <td colspan="3">
                    <img class="mainImage" src="/content/getImage?name=${testOne.imageName}" style="width: 300px; height: 300px"/>
                </td>
            </tr>

            <tr>
                <td>
                    <table id="commentList${testOne.id}" border="1">

                        <g:each var="commentOne" in="${testOne.commentList}">
                            <tr>

                                <td>
                                    ${commentOne.author}
                                </td>
                                <td>
                                    ${commentOne.content}
                                </td>
                            </tr>
                        </g:each>

                    </table>
                </td>

            </tr>
            <tr>
                <td colspan="3">
                    <input type="hidden" value="${testOne.id}" class="testId">
                    <input type="text" value="" class="comment">
                    %{--<input type="button" value="Write" class="btnWrite">--}%
                    <button type="button" class="btn btn-primary btnWrite btn-sm">Write</button>

                </td>
            </tr>

        </g:each>


    </table>
</div>
</body>
</html>

<script id="commentTmpl" type="text/x-handlebars-template">
<tr>
    <td>
        {{author}}
    </td>
    <td>
        {{content}}
    </td>
</tr>
</script>
