import { useState } from 'react';
import axios from 'axios';

export default function ExcelUpload() {
  const [file, setFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    if (selectedFile) {
      // Validate file type
      const allowedTypes = ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/vnd.ms-excel'];
      if (!allowedTypes.includes(selectedFile.type)) {
        setError('Please select an Excel file (.xlsx or .xls)');
        return;
      }
      
      // Validate file size (5MB max)
      if (selectedFile.size > 5 * 1024 * 1024) {
        setError('File size must be less than 5MB');
        return;
      }
      
      setFile(selectedFile);
      setError('');
    }
  };

  const handleUpload = async () => {
    if (!file) {
      setError('Please select a file');
      return;
    }

    setUploading(true);
    setError('');
    setResult(null);

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('/upload/excel/jobs', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });

      setResult(response.data);
      setFile(null);
      document.getElementById('fileInput').value = '';
    } catch (err) {
      setError(err.response?.data?.error || 'Upload failed');
    } finally {
      setUploading(false);
    }
  };

  const downloadTemplate = async () => {
    try {
      const response = await axios.get('/upload/excel/template', {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        responseType: 'blob'
      });

      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'job_template.xlsx');
      document.body.appendChild(link);
      link.click();
      link.remove();
      window.URL.revokeObjectURL(url);
    } catch (err) {
      setError('Failed to download template');
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-blue-600 mb-6">📊 Excel Job Upload</h1>
      
      <div className="bg-white rounded-lg shadow-md p-6 mb-6">
        <h2 className="text-xl font-semibold mb-4">Upload Jobs from Excel</h2>
        
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Select Excel File
          </label>
          <input
            id="fileInput"
            type="file"
            accept=".xlsx,.xls"
            onChange={handleFileChange}
            className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
          />
        </div>

        {error && (
          <div className="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded">
            {error}
          </div>
        )}

        <div className="flex gap-4">
          <button
            onClick={handleUpload}
            disabled={!file || uploading}
            className="bg-blue-500 hover:bg-blue-600 disabled:bg-gray-400 text-white px-6 py-2 rounded-lg font-semibold"
          >
            {uploading ? 'Uploading...' : 'Upload Jobs'}
          </button>
          
          <button
            onClick={downloadTemplate}
            className="bg-green-500 hover:bg-green-600 text-white px-6 py-2 rounded-lg font-semibold"
          >
            📥 Download Template
          </button>
        </div>
      </div>

      {result && (
        <div className="bg-white rounded-lg shadow-md p-6">
          <h3 className="text-lg font-semibold mb-4">Upload Results</h3>
          
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
            <div className="bg-blue-50 p-4 rounded-lg">
              <div className="text-2xl font-bold text-blue-600">{result.totalRows}</div>
              <div className="text-sm text-gray-600">Total Rows</div>
            </div>
            <div className="bg-green-50 p-4 rounded-lg">
              <div className="text-2xl font-bold text-green-600">{result.successCount}</div>
              <div className="text-sm text-gray-600">Successfully Added</div>
            </div>
            <div className="bg-red-50 p-4 rounded-lg">
              <div className="text-2xl font-bold text-red-600">{result.errorCount}</div>
              <div className="text-sm text-gray-600">Errors</div>
            </div>
          </div>

          {result.errors && result.errors.length > 0 && (
            <div className="mb-4">
              <h4 className="font-semibold text-red-600 mb-2">Errors:</h4>
              <ul className="list-disc list-inside text-sm text-red-600">
                {result.errors.map((error, index) => (
                  <li key={index}>{error}</li>
                ))}
              </ul>
            </div>
          )}

          {result.success && (
            <div className="text-green-600 font-semibold">
              ✅ Jobs uploaded successfully!
            </div>
          )}
        </div>
      )}

      <div className="bg-gray-50 rounded-lg p-6 mt-6">
        <h3 className="text-lg font-semibold mb-4">📋 Excel Template Format</h3>
        <p className="text-sm text-gray-600 mb-4">
          Download the template above to see the required format. The Excel file should have the following columns:
        </p>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm">
          <div>
            <h4 className="font-semibold mb-2">Required Columns:</h4>
            <ul className="space-y-1 text-gray-600">
              <li>• <strong>Title:</strong> Job title</li>
              <li>• <strong>Company:</strong> Company name</li>
              <li>• <strong>Location:</strong> Job location</li>
              <li>• <strong>Description:</strong> Job description</li>
            </ul>
          </div>
          <div>
            <h4 className="font-semibold mb-2">Optional Columns:</h4>
            <ul className="space-y-1 text-gray-600">
              <li>• <strong>Type:</strong> FULL_TIME, PART_TIME, INTERNSHIP, CONTRACT</li>
              <li>• <strong>Mode:</strong> ONSITE, REMOTE, HYBRID</li>
              <li>• <strong>Salary:</strong> Salary range</li>
              <li>• <strong>Requirements:</strong> Job requirements</li>
              <li>• <strong>Benefits:</strong> Job benefits</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}
