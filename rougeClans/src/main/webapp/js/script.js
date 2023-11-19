// Sidebar toggle

// theme toggle


function soundWar(){
    var soundWar = new Audio('/media/war_onclick.mp3')
    soundWar.play();
}

const sidebarToggle = document.querySelector("#sidebar-toggle");
sidebarToggle.addEventListener("click",function (){
    document.querySelector("#sidebar").classList.toggle("collapsed");
});

