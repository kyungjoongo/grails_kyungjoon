<!doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<asset:javascript src="jquery-2.2.0.min.js"/>
<asset:javascript src="handlebars-v4.0.10.js"/>
<asset:stylesheet src="bootstrap.css"/>
<asset:javascript src="bootstrap.js"/>

<asset:stylesheet src="toastr.css"/>
<asset:javascript src="toastr.min.js"/>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.3/angular.min.js"></script>
<script>



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
                url: '/test/writeComment',
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
                "timeOut": "3000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr.success('좋아요!!!');
           /*alert("좋아요~");*/

        });
    });

</script>

<div class="container">

    <table class="table" width="600px" border="0">

        <g:each var="testOne" in="${testList}">
            <tr>
                <td>${testOne.id}</td>
                <td>${testOne.name}</td>
                <td>${testOne.content}</td>

            </tr>
            <tr>
                <td colspan="2">
                    <img class="mainImage" src="/test/getImage?name=${testOne.imageName}" style="width: 300px; height: 300px"/>
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
                <td colspan="2">
                    <input type="hidden" value="${testOne.id}" class="testId">
                    <input type="text" value="" class="comment">
                    <input type="button" value="Write" class="btnWrite">
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
