# User Signup Feature

This PR introduces the user signup functionality for the Route Ecommerce API. It includes the complete signup flow with models, services, endpoints configuration, and test cases.

## Key Changes:

### Models:
- `SignUpRequest`: Request model for user registration with fields for name, email, password, confirmation password, and phone

### Services:
- `BaseService`: Base service class that handles RestAssured configuration and environment variable management using dotenv
- `SignUpService`: Service class implementing the signup API call and response validation
- `LogInService` & `CartService`: Placeholder service classes for future implementation

### Configuration:
- Environment file (`.env`) with the API base URL
- Comprehensive endpoint configuration classes for Auth, Cart, Wishlist, Orders, Address, and Catalog endpoints

### Dependencies:
- Added `dotenv-java` for environment variable management
- Added `datafaker` for generating fake test data
- Added `jackson-databind` for JSON serialization/deserialization
- Removed test-scope restriction from `rest-assured` and `testng` to use them in main code

### Tests:
- `SignUpRequestFactory`: Factory class for creating valid test user data with randomly generated emails and Egyptian phone numbers
- `signUpTestCases`: Test class with a test case verifying successful user signup

## Features:
✅ User signup with email validation
✅ Request/response handling with assertions
✅ Environment-based configuration
✅ Test data generation with realistic Egyptian phone numbers
