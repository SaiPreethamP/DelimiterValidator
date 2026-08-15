# Delimiter Validator

A Java program that validates complex expressions containing nested and custom delimiters, using stack-based analysis.

## Overview

This project checks whether delimiters in an expression — parentheses, brackets, braces, or custom-defined delimiter pairs — are correctly matched, properly nested, and complete. It goes beyond simple bracket-matching by supporting **custom delimiter definitions** and providing **position-based error reporting**, so a caller knows exactly where in the input an issue occurred.

## What It Does

- Parses an input expression character by character
- Uses a **stack-based algorithm** to track opening delimiters as they're encountered
- On each closing delimiter, checks the stack to confirm it matches the most recent unmatched opener
- Detects and reports three categories of errors:
  - **Mismatched delimiters** (e.g. `(A]`)
  - **Missing closing delimiters** (unclosed at end of expression)
  - **Unmatched closing delimiters** (a closer with no corresponding opener)
- Reports the **position/index** of each error in the original string, not just that an error occurred

## Why a Stack?

Delimiter matching is a classic stack application: the most recently opened delimiter must be the first one closed (LIFO order). Pushing openers onto a stack and popping/comparing on closers is the natural, efficient way to enforce correct nesting — this project was built specifically to apply that data structure concept from coursework to a working tool.

## Tech Stack

`Java` · Stack (Data Structure) · Custom delimiter configuration

## Skills Demonstrated

Data Structures · Stack-based Algorithm Design · Problem Solving · Error Handling & Reporting

---
*Personal project — Algoma University, Oct 2025–Present*
