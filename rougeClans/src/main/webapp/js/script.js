// Sidebar toggle

// theme toggle

const sidebarToggle = document.querySelector("#sidebar-toggle");
sidebarToggle.addEventListener("click",function (){
    document.querySelector("#sidebar").classList.toggle("collapsed");
});