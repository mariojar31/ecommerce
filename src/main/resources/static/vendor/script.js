// Función para cambiar el estilo del elemento cuando se hace scroll
function changeStyleOnScroll() {
    const targetElement = document.getElementById("target");
    const targetPosition = targetElement.getBoundingClientRect().top;
    const nav_text = document.getElementById("nav_text");
    const windowHeight = window.innerHeight;

    // Cambia el color de fondo si el elemento está a cierta distancia desde arriba
    if (targetPosition <= windowHeight * 0) {
        targetElement.style.backgroundColor = "rgb(36, 36, 36)";
        nav_text.style.textDecoration="none";
        nav_text.style.color="white";

    } else {
        targetElement.style.backgroundColor = "rgb(230, 179, 27)";
        nav_text.style.textDecoration="none";
        nav_text.style.color="rgb(36, 36, 36)";

    }
}

// Escucha el evento de scroll y llama a la función
window.addEventListener("scroll", changeStyleOnScroll);