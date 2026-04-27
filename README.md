# Software Maintenance Lab — Student Handout

**Time:** 40 minutes &nbsp;|&nbsp; **Teams:** 2–3 people &nbsp;|&nbsp; **Tools:** GitHub, JDK 11, Maven, IDE, AI tool of choice

---

## Overview

You've inherited a Java library management system with bugs and documentation debt. You'll triage issues, fix them on branches, use an AI agent to help you out, and merge via pull request — exactly like a real maintenance workflow.

---

## Phase 1 — Setup (0–5 min)

1. **Fork** the starter repo on GitHub (your instructor will share the URL).
2. **Clone** your fork locally:
3. **Run the build:**
   ```bash
   mvn clean install
   ```
   It will fail. Read the output carefully — note the error message and which file caused it.

---

## Phase 2 — Triage with GitHub Issues (5–12 min)

There are bugs seeded in the project. Find all three, then file one GitHub Issue per bug.

### Filing each issue
Required fields for **Bug report**:
- Clear title starting with `[BUG]`
- Steps to reproduce
- Expected vs actual behavior
- Affected file(s)
- Severity label: `bug` or `tech-debt`
- Decide your fix order as a team

---

## Phase 3 — Branch, Fix & Commit (12–22 min)

### Step 1 — Create your branch
```bash
git checkout main
git pull
git checkout -b fix/issue-{NUMBER}-short-description
```

### Step 2 — Make the fix, use AI

### Step 3 — Verify & commit
```bash
mvn test                        # must be green
git add .
git commit -m "fix: <what you did> — closes #N"
git push origin fix/issue-N-your-description
```

---

## Phase 4 — AI-Assisted Review (22–32 min)

### Task A — Ask AI to review your fix

Paste your changed code into your AI tool (Copilot, Claude, ChatGPT, etc.) with this prompt:

> *"Review this Java fix for correctness, edge cases, and maintainability. Are there any cases I haven't handled? Suggest improvements."*

Read the response critically. Don't apply changes blindly.

### Task B — Ask AI to write a test

Prompt your AI tool:

> *"Write a JUnit 5 test for `BookService.searchByTitle()` that covers the case where the input is null. The method should return an empty list. Use `@Test` and `assertEquals`."*

- Add the generated test to `BookServiceTest.java`
- Run `mvn test` — does it pass?
- If the AI's test has bugs, fix them yourself

### Task C — Evaluate (fill this in for the debrief)

| | Your notes |
|---|---|
| One thing AI did well | |AI, when given the correct file for context, was able to pinpoint bugs well.|
| One thing AI got wrong or missed | |AI would make uneeded suggestions, especially if asked to pinpoint an issue in a file that did not have any issues.|
| Did AI's test pass without changes? | No, it needed edits. |

---

## Phase 5 — Pull Request & Peer Review (32–38 min)

### Open your PR

1. Push your branch if you haven't already
2. Go to GitHub → your fork → "Compare & pull request"
3. Set the base branch to `main`
4. Fill in the PR description:

## Debrief (38–40 min)

Be ready to share:

1. **Issues-first discipline:** What changed about your thinking by filing issues before touching code?
2. **AI evaluation:** What did your AI tool get right? What did it miss?
3. **Maven signals:** How did the build output help narrow down Bug 1 without reading every file?

---

## Quick reference

### Branch naming
```
fix/issue-{number}-short-description
```

### Commit message format
```
fix: describe the fix — closes #N
docs: describe the doc change — closes #N
```

### Maven commands
| Command | What it does |
|---|---|
| `mvn clean install` | Compile, test, package |
| `mvn test` | Run tests only |
| `mvn compile` | Compile only |
| `mvn dependency:resolve` | Download/check all dependencies |

### Git workflow
```bash
git checkout -b fix/issue-N-name   # new branch
git status                         # see what changed
git add .                          # stage changes
git commit -m "message"            # commit
git push origin fix/issue-N-name  # push to GitHub
```
