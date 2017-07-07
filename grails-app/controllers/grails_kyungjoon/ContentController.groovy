package grails_kyungjoon

import grails.converters.JSON
import liquibase.util.StringUtils
import org.springframework.web.servlet.ModelAndView

import javax.imageio.ImageIO
import javax.imageio.stream.MemoryCacheImageOutputStream
import java.awt.image.BufferedImage

class ContentController {

    /*static scaffold = Content*/
    static defaultAction = "list"

    final static imagePath = "e:/test/"

    def writeForm() {
    }


    def list() {

        def name = params.get("name")

        List<Content> testList=new ArrayList<>();
        if (StringUtils.isEmpty(name)){
           /* testList = Content.findAllByNameIsNotNull(name, [sort: "id", order: "desc"]);*/

            testList = Content.findAll("from Content as b where b.name is not null order by b.id desc")


        }else{
            testList = Content.findAllByName(name, [sort: "id", order: "desc"]);
        }


        List resultList =new ArrayList()

        for ( Content testOne : testList){
            List commentList = Comment.findAllByTest(testOne)

            testOne.setCommentList(commentList)

            println ("COmmentList"+ commentList.toString())

            for ( Comment commentOne : commentList){
                println (commentOne.getId())
                println (commentOne.getContent())
            }

            resultList.add(testOne);

        }

        return new ModelAndView("/content/list", [testList: resultList])
    }

    def list2(){

    }


    def writeComment(){
        String pComment = params.get("comment");
        def _testId = params.get("_testId")

        def test = Content.findById(_testId);

        def comment = new Comment(author: "kyungjoon", content: pComment, test: test)
        comment.save()

        List commentList = Comment.findAllByTest(test)

        Map resultMap=new HashMap();

        resultMap.put("result", comment)

        render resultMap as JSON

        /*render vehicleList.toList() as JSON*/

    }

    /**
     *  upload image output stream
     * @return
     */
    def getImage() {

        def name = params.get("name");

        def path = imagePath + name
        //returns an image to display
        BufferedImage originalImage = ImageIO.read(new File(path));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(baos)

        def fileext = path.substring(path.indexOf(".") + 1, path.length())
        ImageIO.write(originalImage, fileext, baos);
        baos.flush();
        byte[] img = baos.toByteArray();
        baos.close();
        response.setHeader('Content-length', img.length.toString())
        response.contentType = "image/" + fileext // or the appropriate image content type
        response.outputStream << img
        response.outputStream.flush()
    }

    def write() {
        def f = request.getFile('myFile')

        def name = params.get("name")
        def content = params.get("content")
        def fileName = f.originalFilename

        // HQL
        /*results = Content.executeQuery('select distinct style from Artist')
        println results*/

        def test = new Content(name: name, content: content, pubDate: new Date(), modDate: new Date(), imageName: fileName, commen_id: 0)
        test.save(flush: true, failOnError: true);

        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(view: 'uploadForm')
            return
        } else {

            f.transferTo(new File('e:/content/' + fileName))
            /*List testList = Content.listOrderById(order: "desc");
            return new ModelAndView("/content/list", [testList: testList])*/

            redirect(controller: "Content", action: "list")

        }

    }

    //get the list of files, to create links in the view
    def listImages() {
        List listFiles = IMAGES_DIR.listFiles()
        [images: listFiles]
    }

}
