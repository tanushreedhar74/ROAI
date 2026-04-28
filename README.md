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

Prerequisites: Java 17, Maven, MySQL, Ollama
---


ollama pull nomic-embed-text
ollama pull llama3.2
ollama pull qwen2.5

git clone https://github.com/tanushreedhar74/ROAI.git
cd ROAI
mvn spring-boot:run
---

Open: http://localhost:8080
---

API Endpoints
---
Method	Endpoint	Description
POST	/doc/insert	Store document with embedding
POST	/doc/ask	Ask question (retrieval + AI fallback)
