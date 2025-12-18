// 1. Intentamos recuperar el carrito guardado. Si no existe, inicia vacío.
    let carrito = JSON.parse(localStorage.getItem("carritoGuardado")) || [];
    let usuarioLogueado = null;

    document.addEventListener("DOMContentLoaded", () => {
        verificarSesion();
        cargarProductos();
        actualizarVistaCarrito(); // Actualizamos el número apenas carga la página
    });

    // --- SESIÓN ---
    function verificarSesion() {
        const usuario = localStorage.getItem("usuarioLogueado");
        const btnLogin = document.getElementById("btn-login");

        if (usuario) {
            usuarioLogueado = usuario;
            btnLogin.innerHTML = `👤 ${usuario}`;
            btnLogin.href = "#";
            btnLogin.classList.add("fw-bold");

            // Agregar botón Salir
            if (!document.getElementById("btn-salir")) {
                const li = document.createElement("li");
                li.className = "nav-item ms-2";
                li.innerHTML = `<button id="btn-salir" class="btn btn-outline-light btn-sm" onclick="cerrarSesion()">Salir</button>`;
                document.querySelector(".navbar-nav").appendChild(li);
            }
        }
    }

    function cerrarSesion() {
        localStorage.removeItem("usuarioLogueado");
        window.location.reload();
    }

    // --- CARGAR PRODUCTOS ---
    async function cargarProductos() {
        try {
            const res = await fetch('http://localhost:8080/api/productos');
            const productos = await res.json();
            const contenedor = document.getElementById('contenedor-productos');
            contenedor.innerHTML = '';

            productos.forEach(p => {
                contenedor.innerHTML += `
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <img src="${p.imagenUrl}" class="card-img-top" alt="${p.nombre}">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${p.nombre}</h5>
                                <p class="small text-muted">${p.descripcion}</p>
                                <h4 class="text-primary mt-auto">$${p.precio.toLocaleString('es-CL')}</h4>
                                <button class="btn btn-outline-primary mt-3 w-100"
                                    onclick="agregar('${p.nombre}', ${p.precio})">
                                    Agregar al Carrito 🛒
                                </button>
                            </div>
                        </div>
                    </div>`;
            });
        } catch (e) {
            console.error(e);
            document.getElementById('contenedor-productos').innerHTML = '<p class="text-danger text-center">Error conectando con el servidor.</p>';
        }
    }

    // --- LÓGICA DEL CARRITO CON MEMORIA ---
    function agregar(nombre, precio) {
        carrito.push({ nombre, precio });

        // GUARDAR EN MEMORIA CADA VEZ QUE AGREGAS
        localStorage.setItem("carritoGuardado", JSON.stringify(carrito));

        actualizarVistaCarrito();

        // Animación del botón
        const btn = event.target;
        const textoOriginal = btn.innerText;
        btn.innerText = "¡Agregado!";
        btn.classList.replace("btn-outline-primary", "btn-success");
        setTimeout(() => {
            btn.innerText = textoOriginal;
            btn.classList.replace("btn-success", "btn-outline-primary");
        }, 1000);
    }

    function actualizarVistaCarrito() {
        document.getElementById('contador-carrito').innerText = carrito.length;

        const lista = document.getElementById('lista-carrito');
        lista.innerHTML = '';
        let total = 0;

        if (carrito.length === 0) {
            lista.innerHTML = '<li class="list-group-item text-center text-muted">El carrito está vacío</li>';
        } else {
            carrito.forEach((item) => {
                total += item.precio;
                lista.innerHTML += `
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        ${item.nombre}
                        <span class="badge bg-primary rounded-pill">$${item.precio.toLocaleString('es-CL')}</span>
                    </li>`;
            });
        }
        document.getElementById('total-carrito').innerText = `$${total.toLocaleString('es-CL')}`;
    }

   // --- FINALIZAR COMPRA ---
       async function finalizarCompra() {
           if (carrito.length === 0) {
               alert("El carrito está vacío 😅");
               return;
           }
           if (!usuarioLogueado) {
               if(confirm("⚠️ Debes iniciar sesión para comprar. ¿Ir al Login ahora? (Tu carrito se guardará)")) {
                   window.location.href = "login.html";
               }
               return;
           }

           // Datos para el backend
           const total = carrito.reduce((sum, item) => sum + item.precio, 0);
           const detalle = carrito.map(item => item.nombre).join(", ");

           const pedido = {
               nombreCliente: usuarioLogueado,
               totalCompra: total,
               detalleProductos: detalle
           };

           try {
               const respuesta = await fetch("http://localhost:8080/api/pedidos/crear", {
                   method: "POST",
                   headers: { "Content-Type": "application/json" },
                   body: JSON.stringify(pedido)
               });

               if (respuesta.ok) {
                   alert("✅ ¡Compra realizada con éxito! Tu pedido ha sido procesado.");

                   // LIMPIAR TODO AL FINALIZAR
                   carrito = [];
                   localStorage.removeItem("carritoGuardado");

                   actualizarVistaCarrito();
                   const modal = bootstrap.Modal.getInstance(document.getElementById('modalCarrito'));
                   modal.hide();
               } else {
                   alert("Hubo un error al procesar el pedido.");
               }
           } catch (error) {
               console.error("Error:", error);
               alert("Error de conexión con el servidor.");
           }
       }