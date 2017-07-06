package grails_kyungjoon

import org.springframework.web.servlet.ModelAndView
import spock.lang.Specification

/**
 * Created by NAVER on 2017-07-06.
 */
class TestControllerTest extends Specification {

    @org.junit.Test
    def "List"() {

        List<Test> testList = Test.listOrderById(order: "desc");

        List resultList =new ArrayList()

        for ( Test testOne : testList){
            List commentList = Comment.findAllById(testOne.getId())

            testOne.setCommentList(commentList)

            println ("COmmentList"+ commentList.toString())

            for ( Comment commentOne : CommentList){
                println (commentOne.getId())
                println (commentOne.getContent())
            }

            resultList.add(testOne);

        }



        return new ModelAndView("/test/list", [testList: resultList])

    }
}
