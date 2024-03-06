import React from 'react'

function AdminPage() {
    const 
    name = localStorage.getItem("name")
    const rol = localStorage.getItem("rol")
  return (
    <div>
        <h1>Bienvenido {name}</h1>
        <h2>Rol: {rol}</h2>
    </div>
  )
}

export default AdminPage