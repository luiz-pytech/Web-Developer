var check = $("#hambuguer").is(":checked");
console.log(check);

$(document).ready(function(){
    $(".hambuguer").click(function() {
        if(check){
        $(this).toggleClass("active");
        console.log(check);
        }else{
            $(".menu").toggleClass("active");
        }
    });
});

let loading = document.querySelector('.loading');
window.addEventListener('load', function(){
    loading.style.display = 'none';
})

function selectVideo(source){
    document.getElementById('runningVideo').src = source;
    document.getElementById('runningVideo2').src = source;
    document.getElementById('runningVideo3').src = source;
    document.getElementById('runningVideo4').src = source;
    document.getElementById('runningVideo5').src = source;
    document.getElementById('runningVideo6').src = source;
    document.getElementById('runningVideo7').src = source;
    document.getElementById('runningVideo8').src = source;
}