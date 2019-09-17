package com.github.moqi.faker.weibo.beans

/**
 *
 * created by reol at 2019-09-16
 */

class WeiboCommentBean{

    data class Comment(
        val comments: List<CommentContent>,
        val total_number: Int
    )

    data class CommentContent(
        val created_at: String,
        val id: Long,
        val text: String,
        val user: WeiboStatus.User,
        val reply_comment: CommentContent
    )
}