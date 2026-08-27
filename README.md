# AI Purchasing Agent

An AI-powered purchasing agent that analyzes inventory, demand, supplier constraints, and budget to recommend purchase decisions.

## 🚀 Live Demo

[View Live Project](https://aipurchasingagent-1.onrender.com)

## 🖥️ Backend API

[Backend](https://aipurchasingagent-backend.onrender.com)

## Architecture

`React dashboard → REST controller → PurchasingAgent → PurchasingTools / PurchaseOrderService → JPA entities → H2 demo database or MySQL`

The deterministic agent is deliberately used as a local fallback, so no API key is required. An LLM integration can be added at the agent boundary with `OPENAI_API_KEY`; purchasing data remains retrieved by tools rather than sent in one large prompt.

## Decision process

The agent gets inventory, forecast demand, open PO quantity, supplier data, budget, storage, and calculated cost through `PurchasingTools`. It calculates net need as demand minus on-hand minus inbound stock, caps quantity by budget, supplier availability, and storage, and enforces MOQ. It returns `ACCEPTED`, `MODIFIED`, `REJECTED`, or `INVESTIGATED_FURTHER`, with auditable factors.

For accepted or modified results, it creates a mock PO and `PurchaseOrderService.validate` checks positive quantity, MOQ, availability, budget, and final storage. Failed validation marks the PO `REVIEW_REQUIRED` and escalates to human approval.

## Run

Requirements: Java 17, Maven, and Node 18+.

```powershell
cd backend
mvn spring-boot:run
```

In another terminal:

```powershell
cd frontend
npm install
npm run dev
```

Open the Vite URL (normally `http://localhost:5173`). The API runs at `http://localhost:8080`.

The default data source is an in-memory H2 database for instant demo execution. To use MySQL create/configure a database and run with:

```powershell
$env:SPRING_PROFILES_ACTIVE='mysql'
$env:DB_URL='jdbc:mysql://localhost:3306/purchasing_agent?createDatabaseIfNotExist=true&serverTimezone=UTC'
$env:DB_USERNAME='root'
$env:DB_PASSWORD='your-password'
mvn spring-boot:run
```

JPA creates/updates `Product`, `Supplier`, `PurchaseRecommendation`, and `PurchaseOrder`; inventory, forecast, budget, and storage are intentionally modeled as product/node fields for this focused assignment.

## API

- `GET /api/purchasing/recommendations`
- `GET /api/purchasing/recommendations/{id}`
- `POST /api/purchasing/agent/review/{id}`
- `GET /api/purchase-orders/{id}`
- `POST /api/purchase-orders/{id}/validate`

## Demo scenarios and tests

Seeded recommendations include a 800-unit **MODIFIED** case (inventory + inbound reduces need), an **ACCEPTED** case, and a constraint-conflict **INVESTIGATED_FURTHER** case. Run backend tests with `mvn test`.
