package grails_kyungjoon

class Test {
    String name
    String content
    String pubDate
    String modDate
    String imageName


    static constraints = {

        name maxSize: 255
    }
}
