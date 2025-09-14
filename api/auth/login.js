// api/auth/login.js
export default async function handler(req, res) {
  // Enable CORS
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization');

  if (req.method === 'OPTIONS') {
    return res.status(200).end();
  }

  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  try {
    const { username, password } = req.body;
    
    // Your authentication logic here
    // For now, return a mock response
    if (username && password) {
      const token = 'mock-jwt-token';
      return res.status(200).json({
        token,
        user: {
          id: 1,
          username,
          role: 'STUDENT'
        }
      });
    }

    return res.status(401).json({ error: 'Invalid credentials' });
  } catch (error) {
    return res.status(500).json({ error: 'Internal server error' });
  }
}
