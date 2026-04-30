# ROAI — Retrieval Oriented AI

> A vector database and RAG pipeline built from scratch in Java.  
> Upload any document. Ask any question. ROAI retrieves the answer.

---

## What is ROAI?

ROAI is a retrieval-first AI system that answers queries using stored knowledge.  
It prioritizes user-provided data and only uses AI generation when no relevant data is found.  
Built from scratch without using any external vector database.

---

## How It Works

Your Text → Ollama (nomic-embed-text) → Vector → Stored in memory + MySQL  
↓  
Your Question → Converted to vector → Cosine similarity search  
↓  
High confidence → Exact answer from DB  
Low confidence → AI-generated response  

---

## Features

- Vector similarity search using cosine distance (custom implementation)
- Retrieval-first RAG pipeline
- MySQL persistence (data survives restarts)
- Local LLM integration via Ollama
- Multi-model support (llama3.2, qwen2.5)
- Strict retrieval logic (no modification of stored answers)
- Short query handling (skips DB for simple inputs)
- Simple chat-based UI

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Java 17, Spring Boot |
| Vector Search | Custom cosine similarity |
| Persistence | MySQL |
| AI Models | Ollama (nomic-embed-text, llama3.2, qwen2.5) |
| Frontend | HTML, CSS, JavaScript (basic UI + API calls) |

---

## Run Locally

Prerequisites: 
---
Java 17, Maven, MySQL, Ollama



ollama pull nomic-embed-text

ollama pull llama3.2

ollama pull qwen2.5

git clone https://github.com/tanushreedhar74/ROAI.git
--
cd ROAI

mvn spring-boot:run


Open: http://localhost:8080
---

API Endpoints
---
Method	Endpoint	Description
POST	/doc/insert	Store document with embedding
POST	/doc/ask	Ask question (retrieval + AI fallback)
## Screenshots
<img width="1920" height="1080" alt="Screenshot 2026-04-30 113420" src="https://github.com/user-attachments/assets/5bb713f7-03e0-493e-ad1c-b3c29ecf2b73" />
<img width="1920" height="1080" alt="Screenshot 2026-04-30 113329" src="https://github.com/user-attachments/assets/4c4e317a-5f3f-424d-8bc6-39042ebffdf0" />
<img width="1920" height="1080" alt="Screenshot 2026-04-30 113019" src="https://github.com/user-attachments/assets/02ce3a78-d859-483d-ad98-8ce037121651" />
<img width="1920" height="1080" alt="Screenshot 2026-04-30 112954" src="https://github.com/user-attachments/assets/1d5752eb-701e-4b53-abc3-3b8c0176cbb1" />
<img width="1920" height="1080" alt="Screenshot 2026-04-30 112653" src="https://github.com/user-attachments/assets/78599811-2ffd-4427-8962-b5648ceed027" />
<img width="1920" height="1080" alt="Screenshot 2026-04-30 112556" src="https://github.com/user-attachments/assets/599b3a70-ef13-457d-82ed-859e0d4dcc04" />

