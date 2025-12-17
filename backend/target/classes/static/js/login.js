document.getElementById("loginForm").addEventListener("submit", async (e) => {
        e.preventDefault(); // Evita que se recargue la página

        const user = document.getElementById("username").value;
        const pass = document.getElementById("password").value;

        try {
            const respuesta = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username: user, password: pass })
            });

            const usuarioEncontrado = await respuesta.json();

            if (usuarioEncontrado) {
                // ¡Éxito! Guardamos el nombre en el navegador para usarlo después
                localStorage.setItem("usuarioLogueado", usuarioEncontrado.nombreCompleto);
                window.location.href = "index.html"; // Redirigir al inicio
            } else {
                document.getElementById("error-msg").style.display = "block";
            }

        } catch (error) {
            // Si el backend devuelve "null" o vacío a veces fetch falla al parsear JSON vacío
            document.getElementById("error-msg").style.display = "block";
        }
    });