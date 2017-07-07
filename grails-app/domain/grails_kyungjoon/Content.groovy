package grails_kyungjoon

class Content {
    String name
    String content
    String pubDate
    String modDate
    String imageName
    LikeClickedPerson likeClickedPerson


    List<Comment> commentList

    static hasMany = [comment: Comment, likeClickedPerson: LikeClickedPerson]

    static mappedBy = [Comment: "id", likeClickedPerson: "id"]


    static transients = ['commentList']

    static constraints = {
        name maxSize: 255
        likeClickedPerson nullable: true, blank :true
    }


}
