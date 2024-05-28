# Principios SOLID, Patrones de Diseño y Arquitectura Usada

## Aplicación de Principios SOLID

### Single Responsibility Principle (SRP)
- **Account.java**: 
  - Responsabilidad: Representar una cuenta bancaria.
  - Detalle: Esta clase contiene atributos como `accountNumber`, `owner` y `balance`, y métodos para acceder y modificar estos atributos.
  - Código relevante:
    ```java
    public class Account {
       private String accountNumber;
       private String owner;
       private double balance;
       // Getters y Setters
    }
    ```

- **AccountServiceImpl.java**:
  - Responsabilidad: Gestionar la lógica de negocio relacionada con las cuentas.
  - Detalle: Esta clase maneja la creación de cuentas, depósitos, retiros y obtención de información de cuentas.
  - Código relevante:
    ```java
    public class AccountServiceImpl implements AccountService {
       private AccountDao accountDao;
       public AccountServiceImpl(AccountDao accountDao) {
           this.accountDao = accountDao;
       }
       // Métodos de negocio
    }
    ```

- **BankUI.java**:
  - Responsabilidad: Gestionar la interacción con el usuario.
  - Detalle: Esta clase muestra el menú y procesa las entradas del usuario para realizar operaciones bancarias.
  - Código relevante:
    ```java
    public class BankUI {
       private AccountService accountService;
       public BankUI(AccountService accountService) {
           this.accountService = accountService;
       }
       // Métodos para interactuar con el usuario
    }
    ```

### Open/Closed Principle (OCP)
- **AccountService.java** y **AccountDao.java**:
  - Responsabilidad: Proveer interfaces que pueden ser extendidas sin modificar el código existente.
  - Detalle: Estas interfaces definen los métodos necesarios para las operaciones de cuentas, permitiendo que nuevas implementaciones agreguen funcionalidad sin alterar el contrato definido por las interfaces.
  - Código relevante:
    ```java
    public interface AccountService {
       void createAccount(Account account);
       Account getAccount(String accountNumber);
       void deposit(String accountNumber, double amount);
       void withdraw(String accountNumber, double amount);
    }
    ```

### Liskov Substitution Principle (LSP)
- **AccountServiceImpl** y **AccountDaoImpl**:
  - Responsabilidad: Asegurar que las implementaciones pueden ser reemplazadas por sus respectivas interfaces sin afectar la funcionalidad.
  - Detalle: Estas clases implementan métodos definidos en las interfaces `AccountService` y `AccountDao`, garantizando que cualquier instancia de estas interfaces pueda ser sustituida por las implementaciones sin alterar el comportamiento esperado.
  - Código relevante:
    ```java
    public class AccountServiceImpl implements AccountService {
       private AccountDao accountDao;
       // Implementación de métodos de AccountService
    }
    ```

### Interface Segregation Principle (ISP)
- **AccountService** y **AccountDao**:
  - Responsabilidad: Definir interfaces específicas para manejar funcionalidades particulares.
  - Detalle: `AccountService` maneja las operaciones de negocio de cuentas, mientras que `AccountDao` maneja las operaciones de acceso a datos.
  - Código relevante:
    ```java
    public interface AccountService {
       void createAccount(Account account);
       Account getAccount(String accountNumber);
       void deposit(String accountNumber, double amount);
       void withdraw(String accountNumber, double amount);
    }

    public interface AccountDao {
       void createAccount(Account account);
       Account getAccount(String accountNumber);
       void updateAccount(Account account);
       List<Account> getAllAccounts();
    }
    ```

### Dependency Inversion Principle (DIP)
- **AccountServiceImpl**:
  - Responsabilidad: Depender de abstracciones en lugar de implementaciones concretas.
  - Detalle: `AccountServiceImpl` depende de la abstracción `AccountDao`, permitiendo que diferentes implementaciones de `AccountDao` puedan ser utilizadas sin cambiar la lógica de `AccountServiceImpl`.
  - Código relevante:
    ```java
    public class AccountServiceImpl implements AccountService {
       private AccountDao accountDao;
       public AccountServiceImpl(AccountDao accountDao) {
           this.accountDao = accountDao;
       }
       // Métodos de negocio
    }
    ```

## Aplicación de Patrones de Diseño

### Singleton
- **AccountDaoImpl.java**:
  - Responsabilidad: Asegurar que solo exista una instancia de la clase.
  - Detalle: El patrón Singleton es aplicado para que `AccountDaoImpl` tenga una única instancia a través de la aplicación.
  - Código relevante:
    ```java
    public class AccountDaoImpl implements AccountDao {
       private static AccountDaoImpl instance;
       private Map<String, Account> accounts = new HashMap<>();
       private AccountDaoImpl() {}
       public static AccountDaoImpl getInstance() {
           if (instance == null) {
               instance = new AccountDaoImpl();
           }
           return instance;
       }
       // Métodos de acceso a datos
    }
    ```

### Facade
- **AccountServiceImpl.java**:
  - Responsabilidad: Actuar como una fachada para la lógica de negocio relacionada con las cuentas.
  - Detalle: `AccountServiceImpl` proporciona una interfaz simple para las operaciones de negocio, ocultando la complejidad de las interacciones subyacentes con `AccountDao`.
  - Código relevante:
    ```java
    public class AccountServiceImpl implements AccountService {
       private AccountDao accountDao;
       public AccountServiceImpl(AccountDao accountDao) {
           this.accountDao = accountDao;
       }
       public void createAccount(Account account) {
           accountDao.createAccount(account);
       }
       public Account getAccount(String accountNumber) {
           return accountDao.getAccount(accountNumber);
       }
       public void deposit(String accountNumber, double amount) {
           Account account = accountDao.getAccount(accountNumber);
           if (account != null) {
               account.setBalance(account.getBalance() + amount);
               accountDao.updateAccount(account);
           }
       }
       public void withdraw(String accountNumber, double amount) {
           Account account = accountDao.getAccount(accountNumber);
           if (account != null && account.getBalance() >= amount) {
               account.setBalance(account.getBalance() - amount);
               accountDao.updateAccount(account);
           }
       }
    }
    ```

### Command
- **BankUI.java**:
  - Responsabilidad: Ejecutar las diferentes operaciones de la aplicación basadas en las elecciones del usuario.
  - Detalle: `BankUI` actúa como invocador que ejecuta comandos basados en las entradas del usuario.
  - Código relevante:
    ```java
    public class BankUI {
       private AccountService accountService;
       public BankUI(AccountService accountService) {
           this.accountService = accountService;
       }
       public void showMenu() {
           while (true) {
               System.out.println("1. Create Account");
               System.out.println("2. Deposit");
               System.out.println("3. Withdraw");
               System.out.println("4. Check Balance");
               System.out.println("5. Exit");
               int choice = scanner.nextInt();
               scanner.nextLine(); // Consume newline
               switch (choice) {
                   case 1:
                       createAccount();
                       break;
                   case 2:
                       deposit();
                       break;
                   case 3:
                       withdraw();
                       break;
                   case 4:
                       checkBalance();
                       break;
                   case 5:
                       System.exit(0);
               }
           }
       }
       private void createAccount() {
           System.out.println("Enter Account Number:");
           String accountNumber = scanner.nextLine();
           System.out.println("Enter Owner Name:");
           String owner = scanner.nextLine();
           System.out.println("Enter Initial Balance:");
           double balance = scanner.nextDouble();
           scanner.nextLine();
           Account account = new Account(accountNumber, owner, balance);
           accountService.createAccount(account);
           System.out.println("Account created successfully.");
       }
       private void deposit() {
           System.out.println("Enter Account Number:");
           String accountNumber = scanner.nextLine();
           System.out.println("Enter Amount to Deposit:");
           double amount = scanner.nextDouble();
           scanner.nextLine();
           accountService.deposit(accountNumber, amount);
           System.out.println("Deposit successful.");
       }
       private void withdraw() {
           System.out.println("Enter Account Number:");
           String accountNumber = scanner.nextLine();
           System.out.println("Enter Amount to Withdraw:");
           double amount = scanner.nextDouble();
           scanner.nextLine();
           accountService.withdraw(accountNumber, amount);
           System.out.println("Withdrawal successful.");
       }
       private void checkBalance() {
           System.out.println("Enter Account Number:");
           String accountNumber = scanner.nextLine();
           Account account = accountService.getAccount(accountNumber);
           if (account != null) {
               System.out.println("Account Balance: " + account.getBalance());
           } else {
               System.out.println("Account not found.");
           }
       }
    }
    ```

## Detalles de la Arquitectura Usada

### Arquitectura en 3 Capas

- **Capa de Presentación**:
  - **BankUI.java**: Interfaz con el usuario. Gestiona la interacción con el usuario final, mostrando el menú y recibiendo las entradas del usuario.

- **Capa de Lógica de Negocio**:
  - **AccountServiceImpl.java**, **AccountService.java**: Implementación de la lógica de negocio. Contiene la lógica principal de la aplicación, como la creación de cuentas, depósitos, retiros y verificación de saldos.

- **Capa de Acceso a Datos**:
  - **AccountDaoImpl.java**, **AccountDao.java**: Gestión de la comunicación con la base de datos. Se encarga de realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos de cuentas.

## Resumen

Este proyecto sigue una arquitectura en 3 capas para mantener una separación clara de responsabilidades. Los principios SOLID aseguran que el código sea mantenible y extensible, y los patrones de diseño aplicados ayudan a resolver problemas comunes de manera eficiente. La estructura modular permite que cada componente de la aplicación pueda ser desarrollado y probado de forma independiente.
