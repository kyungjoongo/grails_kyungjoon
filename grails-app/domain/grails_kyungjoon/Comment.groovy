package grails_kyungjoon

class Comment {

    String author
    String content
    String pubDate
    String modDate

    static belongsTo = [test: Content]

    static constraints = {

        author maxSize: 255
        pubDate nullable: true
        modDate nullable: true

    }
}
