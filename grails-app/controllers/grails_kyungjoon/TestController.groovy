package grails_kyungjoon

import grails.web.context.ServletContextHolder
import org.imgscalr.Scalr
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView

import javax.imageio.ImageIO
import javax.imageio.stream.MemoryCacheImageOutputStream
import java.awt.image.BufferedImage

class TestController {

    /*static scaffold = Test*/
    static defaultAction = "list"

    def writeForm() {
    }


    def list() {

        List testList = Test.listOrderById(order: desc);
        return new ModelAndView("/test/list", [testList: testList])
    }


    def getImage() {

        def name = params.get("name");

        def path = "e:/test/" + name
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

        def test = new Test(name: name, content: content, pubDate: new Date(), modDate: new Date(), imageName: fileName)
        test.save(flush: true, failOnError: true);



        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(view: 'uploadForm')
            return
        } else {

            f.transferTo(new File('e:/test/' + fileName))

            /*List testList = Test.getAll()*/

            List testList = Test.listOrderById(order: "desc");
            return new ModelAndView("/test/list", [testList: testList])
        }

    }


    private static final File IMAGES_DIR = new File('/main/webapp/images')

    //get the list of files, to create links in the view
    def listImages() {

        List listFiles = IMAGES_DIR.listFiles()

        [images: listFiles]
    }

}
