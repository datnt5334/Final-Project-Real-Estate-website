const toast = document.querySelector(".mtoast")
closeIcon = document.querySelector(".mclose"),
    progress = document.querySelector(".mprogress");

let timer1, timer2;

document.addEventListener('DOMContentLoaded', () => {
    toast.classList.add("mactive");
    progress.classList.add("mactive");

    timer1 = setTimeout(() => {
        toast.classList.remove("mactive");
    }, 5000); //1s = 1000 milliseconds

    timer2 = setTimeout(() => {
        progress.classList.remove("mactive");
    }, 5300);
}, false);

closeIcon.addEventListener("click", () => {
    toast.classList.remove("mactive");

    setTimeout(() => {
        progress.classList.remove("mactive");
    }, 300);

    clearTimeout(timer1);
    clearTimeout(timer2);
});
