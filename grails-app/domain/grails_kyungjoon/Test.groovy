package grails_kyungjoon

class Test {
    String name
    String content
    String pubDate
    String modDate
    String imageName

    List<Comment> commentList

    static hasMany = [comment: Comment]


    static transients = ['commentList']

    static constraints = {

        name maxSize: 255
    }


}
