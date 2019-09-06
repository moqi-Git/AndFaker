package com.github.moqi.faker.weibo.beans

/**
 *
 * created by reol at 2019-09-03
 */

class WeiboStatus {

    data class TimelineBean(
        val ad: List<Ad>,
        val statuses: List<Statuse>,
        val total_number: Int
    )

    data class Statuse(
        val attitudes_count: Int,
        val biz_feature: String,
        val bmiddle_pic: String,
        val can_edit: Boolean,
        val cardid: String,
        val comment_manage_info: CommentManageInfo,
        val comments_count: Int,
        val content_auth: Int,
        val created_at: String,
        val darwin_tags: List<Any>,
        val favorited: Boolean,
        val geo: Any,
        val gif_ids: String,
        val hasActionTypeCard: Int,
        val hide_flag: Int,
        val hot_weibo_tags: List<Any>,
        val id: Long,
        val idstr: String,
        val in_reply_to_screen_name: String,
        val in_reply_to_status_id: String,
        val in_reply_to_user_id: String,
        val isLongText: Boolean,
        val is_paid: Boolean,
        val is_show_bulletin: Int,
        val mblog_vip_type: Int,
        val mblogtype: Int,
        val mid: String,
        val mlevel: Int,
        val more_info_type: Int,
        val number_display_strategy: NumberDisplayStrategy,
        val original_pic: String,
        val pending_approval_count: Int,
        val pic_num: Int,
        val pic_urls: List<PicUrl>,
        val positive_recom_flag: Int,
        val reposts_count: Int,
        val reward_exhibition_type: Int,
        val rid: String,
        val show_additional_indication: Int,
        val source: String,
        val source_allowclick: Int,
        val source_type: Int,
        val text: String,
        val textLength: Int,
        val text_tag_tips: List<Any>,
        val thumbnail_pic: String,
        val truncated: Boolean,
        val user: User,
        val userType: Int,
        val visible: Visible,
        val retweeted_status: Retweeted.RetweetedStatus
    )

    data class Visible(
        val list_id: Int,
        val type: Int
    )

    data class User(
        val `class`: Int,
        val allow_all_act_msg: Boolean,
        val allow_all_comment: Boolean,
        val avatar_hd: String,
        val avatar_large: String,
        val bi_followers_count: Int,
        val block_app: Int,
        val block_word: Int,
        val cardid: String,
        val city: String,
        val cover_image: String,
        val cover_image_phone: String,
        val created_at: String,
        val credit_score: Int,
        val description: String,
        val domain: String,
        val favourites_count: Int,
        val follow_me: Boolean,
        val followers_count: Int,
        val following: Boolean,
        val friends_count: Int,
        val gender: String,
        val geo_enabled: Boolean,
        val has_service_tel: Boolean,
        val id: String,
        val idstr: String,
        val insecurity: Insecurity,
        val is_guardian: Int,
        val is_teenager: Int,
        val is_teenager_list: Int,
        val lang: String,
        val like: Boolean,
        val like_me: Boolean,
        val location: String,
        val mbrank: Int,
        val mbtype: Int,
        val name: String,
        val online_status: Int,
        val pagefriends_count: Int,
        val pay_date: String,
        val pay_remind: Int,
        val profile_image_url: String,
        val profile_url: String,
        val province: String,
        val ptype: Int,
        val remark: String,
        val screen_name: String,
        val star: Int,
        val statuses_count: Int,
        val story_read_state: Int,
        val tab_manage: String,
        val urank: Int,
        val url: String,
        val user_ability: Int,
        val vclub_member: Int,
        val verified: Boolean,
        val verified_contact_email: String,
        val verified_contact_mobile: String,
        val verified_contact_name: String,
        val verified_level: Int,
        val verified_reason: String,
        val verified_reason_modified: String,
        val verified_reason_url: String,
        val verified_source: String,
        val verified_source_url: String,
        val verified_state: Int,
        val verified_trade: String,
        val verified_type: Int,
        val verified_type_ext: Int,
        val video_status_count: Int,
        val weihao: String
    )

    data class Insecurity(
        val sexual_content: Boolean
    )

    data class PicUrl(
        val thumbnail_pic: String
    )

    data class CommentManageInfo(
        val approval_comment_type: String,
        val comment_permission_type: String
    )

    data class NumberDisplayStrategy(
        val apply_scenario_flag: String,
        val display_text: String,
        val display_text_min_number: String
    )

    data class Ad(
        val id: String,
        val mark: String
    )
}

class Retweeted {
    data class RetweetedStatus(
        val attitudes_count: Int,
        val biz_feature: String,
        val bmiddle_pic: String,
        val can_edit: Boolean,
        val cardid: String,
        val comment_manage_info: CommentManageInfo,
        val comments_count: Int,
        val content_auth: String,
        val created_at: String,
        val darwin_tags: List<Any>,
        val favorited: Boolean,
        val geo: Any,
        val gif_ids: String,
        val hasActionTypeCard: Int,
        val hide_flag: Int,
        val hot_weibo_tags: List<Any>,
        val id: Long,
        val idstr: String,
        val in_reply_to_screen_name: String,
        val in_reply_to_status_id: String,
        val in_reply_to_user_id: String,
        val isLongText: Boolean,
        val is_paid: Boolean,
        val is_show_bulletin: Int,
        val mblog_vip_type: Int,
        val mblogtype: Int,
        val mid: String,
        val mlevel: Int,
        val more_info_type: Int,
        val number_display_strategy: NumberDisplayStrategy,
        val original_pic: String,
        val pending_approval_count: Int,
        val pic_num: Int,
        val pic_urls: List<PicUrl>,
        val positive_recom_flag: Int,
        val reposts_count: Int,
        val reward_exhibition_type: Int,
        val show_additional_indication: Int,
        val source: String,
        val source_allowclick: Int,
        val source_type: Int,
        val text: String,
        val textLength: Int,
        val text_tag_tips: List<Any>,
        val thumbnail_pic: String,
        val truncated: Boolean,
        val user: User,
        val userType: Int,
        val visible: Visible
    )

    data class Visible(
        val list_id: Int,
        val type: Int
    )

    data class PicUrl(
        val thumbnail_pic: String
    )

    data class CommentManageInfo(
        val approval_comment_type: Int,
        val comment_permission_type: Int
    )

    data class User(
        val `class`: Int,
        val allow_all_act_msg: Boolean,
        val allow_all_comment: Boolean,
        val avatar_hd: String,
        val avatar_large: String,
        val bi_followers_count: Int,
        val block_app: Int,
        val block_word: Int,
        val cardid: String,
        val city: String,
        val cover_image: String,
        val cover_image_phone: String,
        val created_at: String,
        val credit_score: Int,
        val description: String,
        val domain: String,
        val favourites_count: Int,
        val follow_me: Boolean,
        val followers_count: Int,
        val following: Boolean,
        val friends_count: Int,
        val gender: String,
        val geo_enabled: Boolean,
        val id: String,
        val idstr: String,
        val insecurity: Insecurity,
        val is_guardian: Int,
        val is_teenager: Int,
        val is_teenager_list: Int,
        val lang: String,
        val like: Boolean,
        val like_me: Boolean,
        val location: String,
        val mbrank: Int,
        val mbtype: Int,
        val name: String,
        val online_status: Int,
        val pagefriends_count: Int,
        val profile_image_url: String,
        val profile_url: String,
        val province: String,
        val ptype: Int,
        val remark: String,
        val screen_name: String,
        val star: Int,
        val statuses_count: Int,
        val story_read_state: Int,
        val tab_manage: String,
        val urank: Int,
        val url: String,
        val user_ability: Int,
        val vclub_member: Int,
        val verified: Boolean,
        val verified_reason: String,
        val verified_reason_url: String,
        val verified_source: String,
        val verified_source_url: String,
        val verified_trade: String,
        val verified_type: Int,
        val video_status_count: Int,
        val weihao: String
    )

    data class Insecurity(
        val sexual_content: Boolean
    )

    data class NumberDisplayStrategy(
        val apply_scenario_flag: Int,
        val display_text: String,
        val display_text_min_number: Int
    )
}