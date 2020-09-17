function upDate(previewPic) {
    const altText = previewPic.alt;
    const imageUrl = previewPic.src;
    const imageDiv = document.getElementById("image")
    imageDiv.style.backgroundImage = "url("+imageUrl+")";
    imageDiv.innerText = altText;
}

function unDo() {
    const imageDiv = document.getElementById("image")
    imageDiv.style.backgroundImage = "url('_')";
    imageDiv.innerText = "Hover over an image below to display here.";
}
