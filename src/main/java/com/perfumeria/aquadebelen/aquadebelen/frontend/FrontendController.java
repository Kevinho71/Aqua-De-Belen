package com.perfumeria.aquadebelen.aquadebelen.frontend;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FrontendController {

    // Sirve el frontend en la raíz: http://localhost:8080/
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String index() {
        return """
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Belleza & Glamour</title>
<style>
  :root{--bg:#0b0b10;--panel:#0E0F14;--muted:#9aa4b2;--primary:#111827;--brand:#111827;--accent:#111827;--chip:#0F172A;--ok:#10b981;--warn:#f59e0b;--danger:#ef4444}
  *{box-sizing:border-box} body{margin:0;background:#0b0b10;color:#fff;font:14px/1.45 system-ui,-apple-system,Segoe UI,Roboto,Ubuntu}
  .bar{border-bottom:1px solid #141826;background:#0c0e14}
  .bar .inner{max-width:1100px;margin:0 auto;padding:12px 16px;display:flex;gap:10px;align-items:center}
  .logo{width:40px;height:40px;border-radius:10px;background:#111827;display:grid;place-items:center}
  .tabs{max-width:1100px;margin:18px auto 0;padding:0 16px}
  .tabs .list{display:grid;grid-template-columns:repeat(5,minmax(0,1fr));gap:8px;background:#0f1220;border-radius:999px;padding:6px}
  .tabs .btn{padding:8px 10px;border-radius:999px;border:none;background:transparent;color:#cbd5e1;cursor:pointer}
  .tabs .btn.active{background:#1f2937;color:#fff}
  .page{max-width:1100px;margin:14px auto;padding:0 16px}
  .grid{display:grid;gap:14px}
  @media(min-width:900px){.grid-4{grid-template-columns:repeat(4,1fr)} .grid-2{grid-template-columns:1fr 1fr}}
  .card{background:#0f1220;border:1px solid #141a2a;border-radius:14px;padding:16px}
  .kpi .t{color:#9aa4b2;font-size:12px;margin-bottom:4px} .kpi .v{font-size:26px;font-weight:700}
  .list table{width:100%;border-collapse:collapse}
  .list th,.list td{border-bottom:1px solid #141a2a;padding:10px;text-align:left}
  .chip{display:inline-block;padding:4px 8px;border-radius:999px;background:#101526;color:#cbd5e1;font-size:12px}
  .chip.warn{background:#3a2907;color:#fcd34d} .chip.danger{background:#3a0b0b;color:#fecaca}
  .muted{color:#9aa4b2}
  .row{display:grid;gap:12px}
  @media(min-width:900px){.row-2{grid-template-columns:1fr 1fr}}
  input,select{width:100%;background:#0b0f1c;border:1px solid #141a2a;border-radius:10px;padding:10px;color:#e5e7eb}
  button.primary{background:#1f2937;border:1px solid #273247;color:#fff;border-radius:10px;padding:10px 14px;cursor:pointer}
  button.primary:disabled{opacity:.5;cursor:not-allowed}
  .right{float:right} .small{font-size:12px}
</style>
</head>
<body>
  <div class="bar">
    <div class="inner">
      <div class="logo">✨</div>
      <div>
        <div style="font-weight:600">Belleza & Glamour</div>
        <div class="muted small">Perfumes y Cosméticos - Sistema de Gestión</div>
      </div>
    </div>
  </div>

  <div class="tabs">
    <div class="list">
      <button class="btn active" data-tab="dashboard">Dashboard</button>
      <button class="btn" data-tab="categories">Categorías</button>
      <button class="btn" data-tab="products">Productos</button>
      <button class="btn" data-tab="add">Agregar</button>
      <button class="btn" data-tab="sales">Ventas</button>
    </div>
  </div>

  <!-- DASHBOARD -->
  <section class="page tab" id="tab-dashboard">
    <h2>Dashboard</h2>
    <p class="muted">Resumen general de perfumes y cosméticos</p>
    <div class="grid grid-4">
      <div class="card kpi"><div class="t">Total Productos</div><div class="v" id="kpi-total-products">-</div></div>
      <div class="card kpi"><div class="t">Stock Bajo</div><div class="v" id="kpi-low-stock">-</div></div>
      <div class="card kpi"><div class="t">Ventas Totales</div><div class="v" id="kpi-total-sales">$0</div></div>
      <div class="card kpi"><div class="t">Ventas Hoy</div><div class="v" id="kpi-today">-</div></div>
    </div>

    <div class="grid row-2" style="margin-top:14px">
      <div class="card">
        <div style="font-weight:600;margin-bottom:8px">Productos con Stock Bajo</div>
        <div id="low-stock-list"></div>
      </div>
      <div class="card">
        <div style="font-weight:600;margin-bottom:8px">Ventas Recientes</div>
        <div class="list">
          <table>
            <thead><tr><th>Producto</th><th>Cantidad</th><th>Total</th><th>Fecha</th></tr></thead>
            <tbody id="recent-sales"></tbody>
          </table>
        </div>
      </div>
    </div>
  </section>

  <!-- CATEGORIES -->
  <section class="page tab" id="tab-categories" style="display:none">
    <h2>Estadísticas por Categoría</h2>
    <p class="muted">Análisis detallado del inventario y ventas por categoría</p>
    <div class="grid row-2" id="category-cards"></div>
  </section>

  <!-- PRODUCTS -->
  <section class="page tab" id="tab-products" style="display:none">
    <h2>Lista de Productos</h2>
    <div class="card list" style="margin-top:10px">
      <table>
        <thead>
          <tr>
            <th>Producto</th><th>Marca</th><th>Categoría</th><th>Precio</th><th>Stock</th>
          </tr>
        </thead>
        <tbody id="products-body"></tbody>
      </table>
    </div>
  </section>

  <!-- ADD PRODUCT -->
  <section class="page tab" id="tab-add" style="display:none">
    <h2>Agregar Nuevo Producto</h2>
    <div class="card" style="max-width:720px">
      <form id="form-add-product" class="row row-2">
        <div>
          <label class="small muted">Nombre del Producto</label>
          <input name="name" placeholder="Ej: Base de Maquillaje Forever" required />
        </div>
        <div>
          <label class="small muted">Marca</label>
          <input name="brand" placeholder="Ej: Dior, Chanel, Maybelline" />
        </div>
        <div>
          <label class="small muted">Precio (USD)</label>
          <input name="price" type="number" step="0.01" value="0" required />
        </div>
        <div>
          <label class="small muted">Categoría (ID)</label>
          <input name="tipoProductoId" type="number" min="1" placeholder="ID de TipoProducto" required />
        </div>
        <div class="row" style="grid-column:1 / -1">
          <button class="primary" type="submit">Agregar Producto</button>
        </div>
      </form>
      <div id="add-product-msg" class="small muted" style="margin-top:10px"></div>
    </div>
  </section>

  <!-- SALES -->
  <section class="page tab" id="tab-sales" style="display:none">
    <h2>Ventas</h2>

    <div class="card" style="max-width:720px;margin-bottom:14px">
      <form id="form-sale" class="row row-2">
        <div>
          <label class="small muted">Cliente (ID)</label>
          <input name="clienteId" type="number" placeholder="ID cliente" required />
        </div>
        <div>
          <label class="small muted">Método de Pago (ID)</label>
          <input name="metodoDePagoId" type="number" placeholder="ID método" required />
        </div>
        <div>
          <label class="small muted">Producto (ID)</label>
          <input name="productoId" type="number" placeholder="ID producto" required />
        </div>
        <div>
          <label class="small muted">Cantidad</label>
          <input name="cantidad" type="number" min="1" value="1" required />
        </div>
        <div>
          <label class="small muted">Descuento</label>
          <input name="descuento" type="number" step="0.01" value="0" />
        </div>
        <div>
          <label class="small muted">Con Factura</label>
          <select name="conFactura">
            <option value="false" selected>No</option>
            <option value="true">Sí</option>
          </select>
        </div>
        <div style="grid-column:1 / -1">
          <button class="primary" type="submit">Registrar Venta</button>
        </div>
      </form>
      <div id="sale-msg" class="small muted" style="margin-top:10px"></div>
    </div>

    <div class="grid grid-4">
      <div class="card kpi"><div class="t">Total de Ventas</div><div class="v" id="sales-total-count">-</div></div>
      <div class="card kpi"><div class="t">Unidades Vendidas</div><div class="v" id="sales-units">-</div></div>
      <div class="card kpi"><div class="t">Ingresos Totales</div><div class="v" id="sales-income">$0</div></div>
      <div class="card kpi"><div class="t"> </div><div class="v"> </div></div>
    </div>

    <div class="card list" style="margin-top:14px">
      <div style="font-weight:600;margin-bottom:8px">Historial de Ventas</div>
      <table>
        <thead><tr><th>Producto</th><th>Cantidad</th><th>Precio Unitario</th><th>Total</th><th>Fecha</th></tr></thead>
        <tbody id="sales-body"></tbody>
      </table>
    </div>
  </section>

<script>
(function(){
  // --- Tabs
  const tabs = document.querySelectorAll('.tabs .btn');
  const sections = {
    dashboard: document.getElementById('tab-dashboard'),
    categories: document.getElementById('tab-categories'),
    products: document.getElementById('tab-products'),
    add: document.getElementById('tab-add'),
    sales: document.getElementById('tab-sales')
  };
  tabs.forEach(b=>{
    b.addEventListener('click', ()=>{
      tabs.forEach(x=>x.classList.remove('active'));
      b.classList.add('active');
      const t = b.dataset.tab;
      Object.keys(sections).forEach(k=>sections[k].style.display = (k===t?'block':'none'));
      if (t==='dashboard') loadDashboard();
      if (t==='categories') loadCategories();
      if (t==='products') loadProducts();
      if (t==='sales') loadSales();
    });
  });

  // --- Helpers
  const fmtMoney = n => new Intl.NumberFormat('en-US',{style:'currency',currency:'USD'}).format(n||0);
  const fmtDate  = s => {
    const d = new Date(s);
    const date = d.toLocaleDateString('es-BO');
    const time = d.toLocaleTimeString('es-BO',{hour12:false});
    return date+' '+time;
  }

  // --- Dashboard
  async function loadDashboard(){
    try{
      const r = await fetch('/api/stats/dashboard');
      const d = await r.json();
      document.getElementById('kpi-total-products').textContent = d.totalProducts;
      document.getElementById('kpi-low-stock').textContent = d.lowStockCount;
      document.getElementById('kpi-total-sales').textContent = fmtMoney(d.totalSalesAmount);
      document.getElementById('kpi-today').textContent = d.todaysSalesCount;

      const pr = await fetch('/api/products'); const products = await pr.json();
      const low = products.filter(p=>p.stock<10).slice(0,5);
      document.getElementById('low-stock-list').innerHTML = low.map(p=>
        `<div style="margin-bottom:8px"><div style="font-weight:600">${p.name}</div>
          <div class="muted">${p.brand||''}</div>
          <span class="chip ${p.stock<5?'danger':(p.stock<10?'warn':'')}">${p.stock} unidades</span></div>`
      ).join('') || '<div class="muted">Sin alertas</div>';

      const sr = await fetch('/api/ventas'); const sales = await sr.json();
      const tbody = document.getElementById('recent-sales');
      tbody.innerHTML = sales.slice(0,5).map(s=>
        `<tr><td>${s.productName}</td><td>${s.quantity}</td><td>${fmtMoney(s.total)}</td><td>${fmtDate(s.date)}</td></tr>`
      ).join('');
    }catch(e){ console.error(e); }
  }

  // --- Categories
  async function loadCategories(){
    try{
      const r = await fetch('/api/stats/categories');
      const cats = await r.json();
      const host = document.getElementById('category-cards');
      host.innerHTML = cats.map(c=>`
        <div class="card">
          <div style="font-weight:600;margin-bottom:6px">${c.category}</div>
          <div class="grid grid-4">
            <div><div class="muted small">Productos</div><div style="font-weight:700">${c.products}</div></div>
            <div><div class="muted small">Stock Total</div><div style="font-weight:700">${c.stockTotal}</div></div>
            <div><div class="muted small">Ventas</div><div style="font-weight:700">${c.ventas}</div></div>
            <div><div class="muted small">Unidades</div><div style="font-weight:700">${c.unidadesVendidas}</div></div>
          </div>
          <div style="margin-top:8px" class="muted small">Valor Inventario: <b>${fmtMoney(c.valorInventario)}</b></div>
          <div class="muted small">Ingresos: <b>${fmtMoney(c.ingresos)}</b></div>
        </div>
      `).join('');
    }catch(e){ console.error(e); }
  }

  // --- Products
  async function loadProducts(){
    try{
      const r = await fetch('/api/products');
      const products = await r.json();
      const tbody = document.getElementById('products-body');
      tbody.innerHTML = products.map(p=>`
        <tr>
          <td>${p.name}</td>
          <td class="muted">${p.brand||''}</td>
          <td>${p.category||'-'}</td>
          <td>${fmtMoney(p.price)}</td>
          <td>${p.stock}</td>
        </tr>
      `).join('');
    }catch(e){ console.error(e); }
  }

  // --- Add Product
  document.getElementById('form-add-product').addEventListener('submit', async (ev)=>{
    ev.preventDefault();
    const f = ev.currentTarget;
    const payload = {
      name: f.name.value,
      brandOrDescription: f.brand.value,
      price: Number(f.price.value||0),
      tipoProductoId: Number(f.tipoProductoId.value)
    };
    const msg = document.getElementById('add-product-msg');
    try{
      const r = await fetch('/api/products',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(payload)});
      if(!r.ok) throw new Error('Error al guardar');
      const id = await r.json();
      msg.textContent = 'Producto creado con ID: '+id;
      f.reset();
      loadProducts();
    }catch(e){
      console.error(e); msg.textContent = 'Error al crear producto';
    }
  });

  // --- Sales (lista y KPIs)
  async function loadSales(){
    try{
      const r = await fetch('/api/ventas');
      const sales = await r.json();
      const tbody = document.getElementById('sales-body');
      tbody.innerHTML = sales.map(s=>`
        <tr>
          <td>${s.productName}</td>
          <td>${s.quantity}</td>
          <td>${fmtMoney(s.total / Math.max(s.quantity,1))}</td>
          <td>${fmtMoney(s.total)}</td>
          <td>${fmtDate(s.date)}</td>
        </tr>
      `).join('');

      const kCount = sales.length;
      const kUnits = sales.reduce((a,b)=>a+Number(b.quantity||0),0);
      const kIncome = sales.reduce((a,b)=>a+Number(b.total||0),0);
      document.getElementById('sales-total-count').textContent = kCount;
      document.getElementById('sales-units').textContent = kUnits;
      document.getElementById('sales-income').textContent = fmtMoney(kIncome);
    }catch(e){ console.error(e); }
  }

  // --- Registrar venta (usa tu endpoint /api/transacciones/registrar)
  document.getElementById('form-sale').addEventListener('submit', async (ev)=>{
    ev.preventDefault();
    const f = ev.currentTarget;
    const payload = {
      clienteId: Number(f.clienteId.value),
      metodoDePagoId: Number(f.metodoDePagoId.value),
      descuento: Number(f.descuento.value||0),
      conFactura: f.conFactura.value === 'true',
      detalles: [
        { productoId: Number(f.productoId.value), cantidad: Number(f.cantidad.value) }
      ]
    };
    const msg = document.getElementById('sale-msg');
    try{
      const r = await fetch('/api/transacciones/registrar',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(payload)});
      if(!r.ok){
        const txt = await r.text();
        throw new Error(txt||'Error al registrar venta');
      }
      const data = await r.json();
      msg.textContent = 'Venta registrada. Total: '+ (data ? (data.totalNeto ?? data.totalBruto ?? 0) : 0);
      loadSales();
      loadDashboard();
    }catch(e){
      console.error(e);
      msg.textContent = 'Error al registrar venta';
    }
  });

  // Primera carga
  loadDashboard();
})();
</script>
</body>
</html>
        """;
    }
}
