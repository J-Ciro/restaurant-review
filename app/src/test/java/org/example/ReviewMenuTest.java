package org.example;

import org.example.commands.*;
import org.example.controllers.*;
import org.example.factories.*;
import org.example.models.*;
import org.example.observers.Observer;
import org.example.repositories.*;
import org.example.services.*;
import org.example.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewMenuTest {
   private IHandler mockHandler;
   private RestaurantMenu menu;
   private RestaurantController mockRestaurantController;
   private ReviewController mockReviewController;
   private DishController dishMockController;
   private MenuController menuMockController;
   private RestaurantService mockRestaurantService;
   private MenuFactory menuFactory;
   private DishFactory dishFactory;
   private ReviewFactory reviewFactory;
   private Observer<Restaurant> restaurantObserver;
   private RestaurantRepository mockRestaurantRepository;

   @BeforeEach
   public void setUp() {
      mockHandler = mock(IHandler.class);
      mockRestaurantController = mock(RestaurantController.class);
      mockReviewController = mock(ReviewController.class);
      dishMockController = mock(DishController.class);
      menuMockController = mock(MenuController.class);
      mockRestaurantService = mock(RestaurantService.class);
      menuFactory = mock(MenuFactory.class);
      dishFactory = mock(DishFactory.class);
      reviewFactory = mock(ReviewFactory.class);
      restaurantObserver = mock(Observer.class);
      mockRestaurantRepository = mock(RestaurantRepository.class);

      menu = new RestaurantMenu(
              mockRestaurantController,
              dishMockController,
              menuMockController,
              mockReviewController,
              mockHandler,
              menuFactory,
              dishFactory,
              reviewFactory,
              restaurantObserver
      );
   }

   @Test
   @DisplayName("Caso 1 del Menu: Mostrar Restaurantes")
   void testMenu1() {
      when(mockHandler.readLine()).thenReturn("1", "0");
      menu.displayMenu();
      verify(mockRestaurantController).showRestaurants();
   }

   @Test
   @DisplayName("Caso 2 del Menu: Agregar Restaurante")
   void testMenu2() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "0");
      menu.displayMenu();
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController);
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese la direccion del restaurante:");
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
   }

   @Test
   @DisplayName("Caso 3 del Menu: Agregar Menu a Restaurante")
   void testMenu3() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "3", "Frisby", "Frisby Menu", "0");
      menu.displayMenu();
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController, menuMockController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
//      inOrder.verify(mockHandler, times(2)).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del menu:");
      inOrder.verify(menuMockController).addMenuToRestaurant("Frisby", menuFactory.createMenu("Frisby Menu", new ArrayList<>()));
   }

   @Test
   @DisplayName("Caso 4 del Menu: Agregar Plato a Menu")
   void testMenu4() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "3", "Frisby", "Frisby Menu", "4", "Frisby", "Frisby Menu", "Frisby Plato", "200.0", "0");
      menu.displayMenu();
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController, menuMockController, dishMockController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
      inOrder.verify(menuMockController).addMenuToRestaurant("Frisby", menuFactory.createMenu("Frisby Menu", new ArrayList<>()));
//      inOrder.verify(mockHandler, times(3)).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del menu:");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato:");
      inOrder.verify(mockHandler).writeLine("Ingrese el precio del plato:");
      inOrder.verify(dishMockController).addDishToMenu("Frisby", "Frisby Menu", dishFactory.createDish("Frisby Plato", 200.0));
   }

   @Test
   @DisplayName("Caso 5 del Menu: Agregar Review a Restaurante")
   void testMenu5() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "5", "Frisby", "Buena comida", "4.0", "0");

      // Configurar el mock de ReviewFactory para devolver un RestaurantReview
      RestaurantReview mockRestaurantReview = new RestaurantReview("Frisby", 4.0, "Buena comida");
      when(reviewFactory.createReview(eq("restaurant"), eq("Frisby"), eq(4.0), eq("Buena comida"), isNull())).thenReturn(mockRestaurantReview);

      menu.displayMenu();

      InOrder inOrder = inOrder(mockHandler, mockRestaurantController, mockReviewController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
      inOrder.verify(mockHandler, times(2)).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese su comentario:");
      inOrder.verify(mockHandler).writeLine("Ingrese la calificacion (1.0 - 5.0):");
      inOrder.verify(mockReviewController).addReviewToRestaurant("Frisby", mockRestaurantReview);
   }

   @Test
   @DisplayName("Caso 6 del Menu: Agregar Review a Plato")
   void testMenu6() {
      // Preparar el comportamiento del mock para la review
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "3", "Frisby", "Frisby Menu", "4", "Frisby", "Frisby Menu", "Frisby Plato", "200.0", "6", "Frisby", "Frisby Menu", "Frisby Plato", "Excelente sabor", "4.5", "0");

      // Simular la entrada del usuario
      DishReview mockDishReview = new DishReview("Frisby Plato", 4.5, "Excelente sabor");
      when(reviewFactory.createReview(eq("dish"), eq("Frisby Plato"), eq(4.5), eq("Excelente sabor"), any())).thenReturn(mockDishReview);

      // Ejecutar el menú
      menu.displayMenu();

      // Verificar las interacciones
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController, menuMockController, dishMockController, mockReviewController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
      inOrder.verify(menuMockController).addMenuToRestaurant("Frisby", menuFactory.createMenu("Frisby Menu", new ArrayList<>()));
      inOrder.verify(dishMockController).addDishToMenu("Frisby", "Frisby Menu", dishFactory.createDish("Frisby Plato", 200.0));
//      inOrder.verify(mockHandler, times(4)).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del menu:");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato:");
      inOrder.verify(mockHandler).writeLine("Ingrese su comentario:");
      inOrder.verify(mockHandler).writeLine("Ingrese la calificacion (1.0 - 5.0):");
      inOrder.verify(mockReviewController).addReviewToDish("Frisby", "Frisby Menu", "Frisby Plato", mockDishReview);
   }

   @Test
   @DisplayName("Caso 7 del Menu: Editar Restaurante")
   void testMenu7() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "7", "Frisby", "Nueva Dirección 456", "0");
      menu.displayMenu();
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
      inOrder.verify(mockHandler, times(2)).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese la nueva direccion del restaurante:");
      inOrder.verify(mockRestaurantController).editRestaurant("Frisby", "Nueva Dirección 456", "Calle 123");
   }

   @Test
   @DisplayName("Caso 8 del Menu: Eliminar Restaurante")
   void testMenu8() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "8", "Frisby", "0");
      menu.displayMenu();
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante a eliminar:");
      inOrder.verify(mockRestaurantController).removeRestaurant("Frisby");
   }

   @Test
   @DisplayName("Caso 9 del Menu: Editar Menu")
   void testMenu9() {
      when(mockHandler.readLine()).thenReturn("2", "Frisby", "Calle 123", "3", "Frisby", "Menu Principal", "4", "Frisby", "Menu Principal", "Pollo Frito", "300.0", "9", "Frisby", "Menu Principal", "2", "Pollo Frito", "Helado", "300.0", "0");
      menu.displayMenu();
      InOrder inOrder = inOrder(mockHandler, mockRestaurantController, menuMockController, dishMockController);
      inOrder.verify(mockRestaurantController).addRestaurant("Frisby", "Calle 123");
      inOrder.verify(menuMockController).addMenuToRestaurant("Frisby", menuFactory.createMenu("Menu Principal", new ArrayList<>()));
      inOrder.verify(dishMockController).addDishToMenu("Frisby", "Menu Principal", dishFactory.createDish("Pollo Frito", 300.0));
      inOrder.verify(mockHandler, times(3)).writeLine("Ingrese el nombre del restaurante:");
      inOrder.verify(mockHandler).writeLine("Ingrese el nombre del menu:");
      inOrder.verify(mockHandler).writeLine("¿Qué desea hacer?");
      inOrder.verify(mockHandler).writeLine("1. Cambiar nombre del menú");
      inOrder.verify(mockHandler).writeLine("2. Editar un plato");
      inOrder.verify(mockHandler).writeLine("3. Eliminar un plato");
      inOrder.verify(mockHandler).writeLine("Ingrese el nuevo nombre del plato (dejar en blanco para mantener el actual):");
      inOrder.verify(mockHandler).writeLine("Ingrese el nuevo precio del plato (dejar en blanco para mantener el actual):");
      inOrder.verify(dishMockController).editDish("Frisby", "Menu Principal", "Pollo Frito", "Helado", 300.0);
   }

   @Test
   @DisplayName("Caso de opción inválida")
   void testInvalidOption() {
      when(mockHandler.readLine()).thenReturn("10", "0");
      menu.displayMenu();
      verify(mockHandler).writeLine("Opcion no valida. Intentelo de nuevo.");
   }

   @Test
   @DisplayName("Caso de entrada no numérica")
   void testNonNumericInput() {
      when(mockHandler.readLine()).thenReturn("abc", "0");
      menu.displayMenu();
      verify(mockHandler).writeLine("Opcion no válida. Intentelo de nuevo.");
   }
}