package grails_kyungjoon

import grails.web.context.ServletContextHolder
import org.imgscalr.Scalr
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class TestController {

    /*static scaffold = Test*/
    static defaultAction = "list"

    def writeForm() {


    }


    def list(){

        List testList = Test.getAll()
        return new ModelAndView("/test/list", [testList: testList] )
    }


    def getImage() {

        def name = params.get("name");

        def path = "e:/test/"+ name



        //returns an image to display
        BufferedImage originalImage = ImageIO.read(new File(path));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        def fileext = path.substring(path.indexOf(".") + 1, path.length())

        ImageIO.write(originalImage, fileext, baos);
        baos.flush();

        byte[] img = baos.toByteArray();

        BufferedImage thumbnail = Scalr.resize(img, 150);
        baos.close();
        response.setHeader('Content-length', BufferedImage.length.toString())
        response.contentType = "image/" + fileext // or the appropriate image content type
        response.outputStream << BufferedImage
        response.outputStream.flush()
    }

    def String uploadFile(MultipartFile file, String name, String destinationDirectory ) {

        def serveletContext = ServletContextHolder.servletContext
        def storagePath = serveletContext.getRealPath( destinationDirectory )
        def storagePathDirectory = new File( storagePath )

        if( !storagePathDirectory.exists() ){
            println("creating directory ${storagePath}")
            if(storagePathDirectory.mkdirs()){
                println "SUCCESS"
            }else{
                println "FAILED"
            }
        }

        // Store file

        if(!file.isEmpty()){
            file.transferTo( new File("${storagePath}/${name}") )
            println("Saved File: ${storagePath}/${name}")
            return "${storagePath}/${name}"
        }else{
            println "File: ${file.inspect()} was empty"
            return null
        }
    }


    def write() {
        def f = request.getFile('myFile')

        def p = new Test(name: "Fred", content : "sdlkfsldkflskdflk고경준천재님", pubDate: new Date())
        p.save()


        def fileName = f.originalFilename

        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(view: 'uploadForm')
            return
        }else{

                f.transferTo(new File('e:/test/'+ fileName))
/*
            *//*def webrootDir = servletContext.getRealPath("/") //app directory*//*
*//*
            def webrootDir =servletContext.getContextPath()
            File fileDest = new File(webrootDir, "images/"+ fileName)
            f.transferTo(fileDest)/*//*//*

            *//*f.transferTo(new File('e://test//'))*//*

            def storagePath = servletContext.getRealPath( "/assets/images/" )

            File fileDest = new File(storagePath+ fileName)
            f.transferTo(fileDest)*/



            List testList = Test.getAll()
            return new ModelAndView("/test/list", [testList: testList] )
        }

    }




    private static final File IMAGES_DIR = new File('/main/webapp/images')

    //get the list of files, to create links in the view
    def listImages() {

        List listFiles = IMAGES_DIR.listFiles()

        [images: listFiles]
    }
    //get the content of a image
    def displayImage() {
        File image = new File(IMAGES_DIR.getAbsoluteFilePath() + File.separator + params.img)
        if(!image.exists()) {
            response.status = 404
        } else {
            response.setContentType("application/jpg")
            OutputStream out = response.getOutputStream();
            out.write(avatarFilePath.bytes);
            out.close();
        }
    }
}
