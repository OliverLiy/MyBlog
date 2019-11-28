function post() {
    var questionid = $("#question_id").val();
    var content = $("#content").val();
    if (content == '') {
        alert("回复内容不能为空")
    } else {
        $.ajax({
            type: "POST",
            url: "/comment",
            contentType: 'application/json',
            data: JSON.stringify({
                "parent_id": questionid,
                "type": 1,
                "content": content
            }),
            success: function (data) {
                if (data.code == 200) {
                    window.location.reload();
                } else {
                    alert("出现了错误");
                }
            },
            dataType: "json"
        });
    }
}

function secondcomment(e) {
    var id = e.getAttribute("data-id");
    var check = e.getAttribute("data-check");
    var comment = $("#comment-" + id);
    //如果check为1则展开二级评论，否则收缩
    if (check == "1") {
        $.getJSON("/comment/" + id, function (data) {
            var subCommentContainer = $("#comment-" + id);
            //如果子元素的长度为1，即第一次添加，则调用下面的方法
            if (subCommentContainer.children().length == 1) {
                $.each(data.data.reverse(), function (index, comment) {
                    //对应<span th:text="${comment.user.name}"/>
                    var usernameElement = $("<span/>", {
                        html: comment.user.name
                    });
                    //对应<div style="font-size: 15px; margin-top:5px;"
                    //      th:text="${comment.content}">
                    //    </div>
                    var contentElement = $("<div/>", {
                        "style": "font-size: 15px; margin-top:5px;",
                        html: comment.content
                    });

                    var timeElement = $("<span/>", {
                        "style": "float: right",
                        html: moment(comment.createtime).format('YYYY-MM-DD')
                    });
                    var questionmenuElement = $("<div/>", {
                        "class": "question-menu"
                    }).append(timeElement);

                    var imgElement = $("<img/>", {
                        "class": "media-object img-rounded picset",
                        "src": comment.user.headpic
                    });

                    var medialeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append(imgElement);

                    var mediaheadingElement = $("<h4/>", {
                        "class": "media-heading",
                    }).append(usernameElement)
                        .append(contentElement)
                        .append(questionmenuElement);

                    var mediabodyElement = $("<div/>", {
                        "class": "media-body",
                    }).append(mediaheadingElement);

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(medialeftElement)
                        .append(mediabodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-ss-12 comments",
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                })
            }
        });
        comment.addClass("in");
        e.setAttribute("data-check", "0");
        e.classList.add("active");

    } else {
        comment.removeClass("in");
        e.setAttribute("data-check", "1")
        e.classList.remove("active");
    }
}

function replypost(e) {
    var commentid = e.getAttribute("data-id");
    var content = $("#input-" + commentid).val();
    if (content == '') {
        alert("回复内容不能为空")
    } else {
        $.ajax({
            type: "POST",
            url: "/comment",
            contentType: 'application/json',
            data: JSON.stringify({
                "parent_id": commentid,
                "type": 2,
                "content": content
            }),
            success: function (data) {
                if (data.code == 200) {
                    window.location.reload();
                } else {
                    alert("出现了错误");
                }
            },
            dataType: "json"
        });

        console.log(commentid);
        console.log(content);
    }
}
function showselecttag() {
    $("#select-tags").show();
}
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    //获取当前的标签内容
    var previous = $("#tag").val();
    //如果标签不存在，则添加
    if (previous.indexOf(value) == -1){
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}