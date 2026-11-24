# Admin WorkSpace — Angular 21 User Admin Frontend

A clean, premium-but-understated people directory frontend for the Spring Boot CRUD API.

## Requirements

- Node.js 20.19+ (Node 22 recommended)
- npm

## Run

```bash
npm install
npm start
```

Open http://localhost:4200.

## API

The frontend expects Spring Boot at `http://localhost:8080`:

- `GET /api/users` (wrapper response with `data` array)
- `GET /api/users/{id}`
- `POST /api/users` multipart fields `first_name`, `last_name`, `email`, `password`, optional `image`
- `PUT /api/users/{id}` multipart fields `first_name`, `last_name`, `email`, optional `image`
- `DELETE /api/users/{id}`

Uploaded image URL is assumed to be `http://localhost:8080/uploads/{filename}`. Update `UserService` if your backend exposes a different path.

## Backend notes

- Configure CORS for `http://localhost:4200`.
- Expose your upload directory through `/uploads/**` for image previews.
- This UI is not a substitute for API authorization; secure your backend before production.
