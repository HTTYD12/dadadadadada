// Función para cargar la página principal
function index() {
    return HtmlService.createTemplateFromFile('index')
      .evaluate()
      .setTitle('Inicio')
      .setSandboxMode(HtmlService.SandboxMode.IFRAME)
      .setXFrameOptionsMode(HtmlService.XFrameOptionsMode.ALLOWALL)
      .addMetaTag('viewport', 'width=device-width, initial-scale=1');
  }
  
  // Función para manejar la solicitud GET y mostrar la página principal
  function doGet() {
    return index();
  }
  
  // Función para manejar la solicitud POST desde el formulario de registro
  function register() {
    var sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName('Usuarios');
    var data = [form.nombre, form.email, form.password];
    sheet.appendRow(data);
    
    // Cargar la página de éxito "success.html" y pasar el nombre del usuario como parámetro
    var successPage = HtmlService.createTemplateFromFile('success');
    successPage.nombre = form.nombre;
    return successPage.evaluate()
      .setTitle('Registro exitoso')
      .setSandboxMode(HtmlService.SandboxMode.IFRAME)
      .setXFrameOptionsMode(HtmlService.XFrameOptionsMode.ALLOWALL)
      .addMetaTag('viewport', 'width=device-width, initial-scale=1');
  }
  
  // Función para validar el formato de un correo electrónico
  function isValidEmail(email) {
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
  
  // Función para mostrar la página de inicio de sesión
  function login() {
    return HtmlService.createHtmlOutputFromFile('login')
      .setTitle('Iniciar sesión')
      .setSandboxMode(HtmlService.SandboxMode.IFRAME)
      .setXFrameOptionsMode(HtmlService.XFrameOptionsMode.ALLOWALL)
      .addMetaTag('viewport', 'width=device-width, initial-scale=1');
  }
  
  // Función para validar las credenciales de inicio de sesión
  function validateLogin(email, password) {
    var sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName('Usuarios');
    var data = sheet.getDataRange().getValues();
    for (var i = 0; i < data.length; i++) {
      if (data[i][1] === email && data[i][2] === password) {
        return true;
      }
    }
    return false;
  }
  