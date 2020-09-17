function getGithubInfo(user, callback) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "https://api.github.com/users/"+user, true);
    xhr.onload = function (e) {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
          callback(xhr)
        } else {
            callback(xhr)
        }
      }
    };
    xhr.send(null);
}

function showUser(user) {
    $('#profile').show();
    $('#profile h5.card-title').text(user.login)
    $('#profile img.card-img-top').attr('src', user.avatar_url)
    $('#profile p.card-text').text('User Name : ' + user.name)
    $('#profile a').attr('href', user.url)
}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    $('#profile').hide();
    $('#error').text("There is no such username.Please enter a valid username")
}

$(document).ready(function () {
    $('#profile').hide();
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            if (username) {
                $('#error').text("")
                getGithubInfo(username, function(response) {
                    //if the response is successful show the user's details
                        if (response && response.status == 200) {
                            showUser(JSON.parse(response.responseText));
                            //else display suitable message
                        } else {
                            noSuchUser(username);
                        }
                    });
            } else {
                $('#profile').hide();
                $('#error').text("Please enter a username.")
            }
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse


        }
    })
});
