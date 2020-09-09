// validate password
var password = document.getElementById("password")
    , confirm_password = document.getElementById("confirm-password");

function validatePassword(){
    if(password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}

if (password != null && confirm_password != null) {
    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
}

// listen for event to edit post
var editPost = document.getElementById("post-section-listener");

function editPostContent(evt) {
    var accessId = evt.target.getAttribute("post-access-id");
    if (accessId != null) {
        let contentToEdit = document.getElementById(accessId).textContent.trim();
        var editWindow = document.getElementById("message-text");

        editWindow.innerText = contentToEdit
        let postId = evt.target.getAttribute("post-id");
        let form = document.getElementById("edit-form");
        form.setAttribute("action", "/post/edit_post/" + postId);
        console.log(contentToEdit);
        console.log(postId);
    }
}

if (editPost != null) {
    editPost.addEventListener("click",editPostContent,);
}

//listen for event to edit comment
var editComment = document.getElementById("comment-section-listener");

function editCommentContent(evt) {
    var accessId = evt.target.getAttribute("comment-access-id");
    if (accessId != null) {
        console.log(accessId)
        let contentToEdit = document.getElementById(accessId).textContent.trim();
        var editWindow = document.getElementById("message-text2");

        editWindow.innerText = contentToEdit
        let postId = evt.target.getAttribute("comment-id");
        let form = document.getElementById("comment-form");
        form.setAttribute("action", "/comment/edit_comment/" + postId);
        console.log(contentToEdit);
        console.log(postId);
    }
}

if (editComment != null) {
    editComment.addEventListener("click", editCommentContent,);
}