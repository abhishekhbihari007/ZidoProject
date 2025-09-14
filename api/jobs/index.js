// api/jobs/index.js
export default async function handler(req, res) {
  // Enable CORS
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization');

  if (req.method === 'OPTIONS') {
    return res.status(200).end();
  }

  try {
    switch (req.method) {
      case 'GET':
        // Mock job listings
        return res.status(200).json([
          {
            id: 1,
            title: 'Frontend Developer',
            company: 'Tech Corp',
            location: 'Remote',
            type: 'FULL_TIME',
            mode: 'REMOTE',
            description: 'Looking for a skilled React developer...'
          },
          {
            id: 2,
            title: 'Backend Developer',
            company: 'Startup Inc',
            location: 'New York',
            type: 'FULL_TIME',
            mode: 'HYBRID',
            description: 'Spring Boot and microservices expert needed...'
          }
        ]);

      case 'POST':
        // Create new job
        const newJob = { id: Date.now(), ...req.body };
        return res.status(201).json(newJob);

      default:
        return res.status(405).json({ error: 'Method not allowed' });
    }
  } catch (error) {
    return res.status(500).json({ error: 'Internal server error' });
  }
}
