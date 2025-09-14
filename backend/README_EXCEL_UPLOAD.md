# Excel Data Upload Feature

## Overview
The Excel data upload feature allows recruiters to bulk upload job postings using Excel files. This feature includes:

- **File Upload**: Upload Excel files (.xlsx, .xls)
- **Data Validation**: Validate job data before saving
- **Template Download**: Download Excel template with proper format
- **Error Reporting**: Detailed error messages for invalid data
- **Bulk Processing**: Process multiple jobs at once

## API Endpoints

### 1. Upload Jobs from Excel
```
POST /api/upload/excel/jobs
Content-Type: multipart/form-data
Authorization: Bearer <token>

Body: file (Excel file)
```

**Response:**
```json
{
  "success": true,
  "totalRows": 10,
  "successCount": 8,
  "errorCount": 2,
  "errors": ["Row 3: Title is required", "Row 7: Invalid job type"],
  "jobs": [...]
}
```

### 2. Download Excel Template
```
GET /api/upload/excel/template
Authorization: Bearer <token>
```

**Response:** Excel file download

### 3. Upload Resume
```
POST /api/upload/resume
Content-Type: multipart/form-data
Authorization: Bearer <token>

Body: file (PDF/DOC/DOCX file)
```

## Excel Template Format

The Excel template should have the following columns:

| Column | Field | Required | Description |
|--------|-------|----------|-------------|
| A | Title | Yes | Job title |
| B | Company | Yes | Company name |
| C | Location | Yes | Job location |
| D | Type | No | FULL_TIME, PART_TIME, INTERNSHIP, CONTRACT |
| E | Mode | No | ONSITE, REMOTE, HYBRID |
| F | Salary | No | Salary range |
| G | Description | Yes | Job description |
| H | Requirements | No | Job requirements |
| I | Benefits | No | Job benefits |

## Sample Excel Data

| Title | Company | Location | Type | Mode | Salary | Description | Requirements | Benefits |
|-------|---------|----------|------|------|--------|-------------|--------------|----------|
| Software Engineer | Tech Corp | New York | FULL_TIME | ONSITE | $80,000 - $100,000 | Develop software applications | Java, Spring Boot | Health insurance, 401k |
| Frontend Developer | Startup Inc | Remote | FULL_TIME | REMOTE | $70,000 - $90,000 | Build user interfaces | React, TypeScript | Flexible hours |
| Data Analyst | Data Corp | San Francisco | INTERNSHIP | HYBRID | $25/hour | Analyze data trends | Python, SQL | Learning opportunities |

## File Validation

### Excel Files
- **Allowed Types**: .xlsx, .xls
- **Max Size**: 5MB
- **Required Columns**: Title, Company, Location, Description

### Resume Files
- **Allowed Types**: .pdf, .doc, .docx
- **Max Size**: 10MB
- **User Type**: Students only

## Error Handling

The system provides detailed error messages for:
- Invalid file types
- File size limits
- Missing required fields
- Invalid enum values
- Database constraints

## Frontend Integration

The frontend includes:
- File upload component with drag-and-drop
- Progress indicators
- Error display
- Success feedback
- Template download button

## Security

- JWT authentication required
- Role-based access (recruiters for job upload, students for resume upload)
- File type validation
- File size limits
- Secure file storage

## Usage

1. **Download Template**: Click "Download Template" to get the Excel format
2. **Fill Data**: Add your job data following the template format
3. **Upload File**: Select and upload your Excel file
4. **Review Results**: Check the upload results and any errors
5. **Fix Errors**: Correct any errors and re-upload if needed

## Technical Implementation

- **Backend**: Spring Boot with Apache POI for Excel processing
- **Frontend**: React with file upload handling
- **Storage**: Local file system with organized directories
- **Validation**: Server-side validation with detailed error reporting
