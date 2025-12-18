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
                localStorage.setItem("usuarioLogueado", usuarioEncontrado.nombreCompleto);
                window.location.href = "index.html"; // Redirigir al inicio
            } else {
                document.getElementById("error-msg").style.display = "block";
            }

        } catch (error) {
            document.getElementById("error-msg").style.display = "block";
        }
    });